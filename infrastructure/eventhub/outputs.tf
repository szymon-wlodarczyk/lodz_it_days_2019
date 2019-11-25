output "eventhub_authorization_rule_connection_string" {
  value = "${azurerm_eventhub_authorization_rule.eventhub_authorization_rule.primary_connection_string}"
}
