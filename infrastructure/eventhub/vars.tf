variable "eventhub_namespace_name" {
  description = "Eventhub namespace name that will contain various eventhubs"
}

variable "eventhub_name" {
  description = "Eventhub name"
}

variable "eventhub_consumer_group_name" {
  description = "Consumer group"
}

variable "eventhub_partition_count" {
  description = "Number of eventhub partitions"
}

variable "resource_group_name" {
  description = "Name of the resource group"
}

variable "location" {
  description = "The default Azure region for the resource provisioning. Not all resources available across Azure regions"
}

variable "azurerm_eventhub_authorization_rule_name" {}

variable "Sku" {
  default = "Standard"
}

variable "capacity" {
  default = 1
}

variable "auto_inflate_enabled" {
  default = true
}

variable "maximum_throughput_units" {
  default = 10
}

variable "eventhub_storage_name" {
  default = "ldi2019events"
  description = "Event Hub storage name"
}

variable "account_replication_type" {
  default = "LRS"
  description = "Replication type storage account"
}

