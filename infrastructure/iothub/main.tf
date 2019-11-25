resource "azurerm_iothub" "iothub" {
  name                = "${var.iothub_name}"
  resource_group_name = "${var.resource_group_name}"
  location            = "${var.location}"
  sku {
    name = "${var.sku_name}"
    tier = "${var.sku_tier}"
    capacity = "${var.sku_capacity}"
  }
  endpoint {
    name = "${var.endpoint_name}"
    type = "${var.endpoint_type}"
    connection_string = "${var.endpoint_connection_string}"
  }
  route = [
    {
      name = "device-events-routing"
      source = "DeviceMessages"
      condition = "true"
      endpoint_names = ["LDI-2019-endpoint"]
      enabled = true
    }
  ]
}
