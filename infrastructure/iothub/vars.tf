variable "iothub_name" {
  description = "Azure IoTHub name"
}

variable "resource_group_name" {
  description = "Azure Resource Group name"
}

variable "location" {
  description = "Azure region to deploy"
}

variable "sku_name" {
  default = "S2"
}

variable "sku_tier" {
  default = "Standard"
}

variable "sku_capacity" {
  default = "1"
}

variable "endpoint_name" {
  default = "endpoint"
}

variable "endpoint_type" {
  default = "AzureIotHub.EventHub"
}

variable "endpoint_connection_string" {}

variable "service_principal_app_id" {}
variable "service_principal_password" {}
variable "service_principal_tenant" {}

variable "iot_tags" {
  description = "Tags to be applied to IoT Hub"
  type = "map"
  default = {}
}
