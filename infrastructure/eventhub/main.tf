resource "azurerm_eventhub_namespace" "eventhubnamespace" {
  name                     = "${var.eventhub_namespace_name}"
  location                 = "${var.location}"
  resource_group_name      = "${var.resource_group_name}"
  sku                      = "${var.Sku}"
  capacity                 = "${var.capacity}"
  auto_inflate_enabled     = "${var.auto_inflate_enabled}"
  maximum_throughput_units = "${var.maximum_throughput_units}"
  kafka_enabled 	   = true
}

resource "azurerm_eventhub" "eventhub" {
  name                = "${var.eventhub_name}"
  namespace_name      = "${azurerm_eventhub_namespace.eventhubnamespace.name}"
  resource_group_name = "${var.resource_group_name}"
  partition_count     = "${var.eventhub_partition_count}"
  message_retention   = 4
  depends_on          = ["azurerm_eventhub_namespace.eventhubnamespace"]
}

resource "azurerm_eventhub_authorization_rule" "eventhub_authorization_rule" {
  name                = "${var.azurerm_eventhub_authorization_rule_name}"
  namespace_name      = "${azurerm_eventhub_namespace.eventhubnamespace.name}"
  eventhub_name       = "${azurerm_eventhub.eventhub.name}"
  resource_group_name = "${var.resource_group_name}"
  listen              = false
  send                = true
  manage              = false
  depends_on          = ["azurerm_eventhub.eventhub"]
}

resource "azurerm_eventhub_consumer_group" "consumer_group" {
  name                = "${var.eventhub_consumer_group_name}"
  namespace_name      = "${azurerm_eventhub_namespace.eventhubnamespace.name}"
  eventhub_name       = "${azurerm_eventhub.eventhub.name}"
  resource_group_name = "${var.resource_group_name}"
  depends_on          = ["azurerm_eventhub.eventhub"]
}

resource "azurerm_storage_account" "eventhub_storage" {
  name                     = "${var.eventhub_storage_name}"
  resource_group_name      = "${var.resource_group_name}"
  location                 = "${var.location}"
  account_tier             = "Standard"
  account_replication_type = "${var.account_replication_type}"
}
resource "azurerm_storage_container" "storage_container" {
  name                  = "events-processing-service"
  resource_group_name   = "${var.resource_group_name}"
  storage_account_name  = "${azurerm_storage_account.eventhub_storage.name}"
  container_access_type = "private"
}
