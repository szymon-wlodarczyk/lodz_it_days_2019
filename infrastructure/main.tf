provider "azurerm" {
  version = "=1.36.1"

  # All of these has to be set either here or with ARM_XYZ env variables
  # https://www.terraform.io/docs/providers/azurerm/index.html#argument-reference
  
  # You have to create Service principal for this
  # https://docs.microsoft.com/en-us/cli/azure/create-an-azure-service-principal-azure-cli?toc=%2Fazure%2Fazure-resource-manager%2Ftoc.json&view=azure-cli-latest

  client_id = ""
  client_secret = ""
  tenant_id = ""
  subscription_id= ""

}

resource "azurerm_resource_group" "resource_group" {
  location = "${var.location}"
  name = "ldi-resource-group-terraform"
}

resource "azurerm_resource_group" "resource_group_azure_container" {
  name     = "ldi-resource-group-container-service-terraform"
  location = "${var.location}"
}

resource "azurerm_container_service" "azure_container_service" {
  name                   = "ldi2019-acs-terraform"
  location               = "${var.location}"
  resource_group_name    = "ldi-resource-group-container-service-terraform"
  orchestration_platform = "Swarm"

  master_profile {
    count      = 1
    dns_prefix = "ldi2019-acs-terraform"
  }

  linux_profile {
    admin_username = "ldi2019swarmadmin"

    ssh_key {
      # put SSH key here
      key_data = ""
    }
  }

  agent_pool_profile {
    name       = "default"
    count      = 1
    dns_prefix = "ldi2019-dns-prefix"
    vm_size    = "Standard_F2"
  }

  diagnostics_profile {
    enabled = true
  }

}


module "eventhub" {
  source = "./eventhub"
  eventhub_namespace_name = "ldi-ehub-ns"
  eventhub_name = "ldi-ehub"
  eventhub_consumer_group_name = "ldi-consumer-group"
  eventhub_partition_count = "1"
  resource_group_name = "${azurerm_resource_group.resource_group.name}"
  location = "${var.location}"
  azurerm_eventhub_authorization_rule_name = "iot-ehub-rule"
}

module "iothub" {
  source = "./iothub"
  iothub_name = "ldi2019-iothub"
  resource_group_name = "${azurerm_resource_group.resource_group.name}"
  location = "${var.location}"
  endpoint_connection_string = "${module.eventhub.eventhub_authorization_rule_connection_string}"
  service_principal_app_id = "${var.service_principal_app_id}"
  service_principal_password = "${var.service_principal_password}"
  service_principal_tenant = "${var.service_principal_tenant}"
}

module "postgres" {
  source = "./postgres"
  postgresql_server_name = "ldi2019db"
  location = "${var.location}"
  resource_group_name = "${azurerm_resource_group.resource_group.name}"
  postgresql_server_login = "${var.postgresql_server_login}"
  postgresql_server_password = "${var.postgresql_server_password}"
  postgresql_version = "10.0"
  postgresql_database_name = "ldi2019db"
}
