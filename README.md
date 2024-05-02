# eshop

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=wasd0_eshop&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=wasd0_eshop)

## Interaction of services

```mermaid
sequenceDiagram
    actor customer
    participant order_s as order <br> microservice
    participant customer_s as customer <br> microservice
    participant product_s as product <br> microservice
    customer ->> order_s: order request created
    order_s ->> customer_s: order created pending
    customer_s -->> order_s: money reserved
    order_s ->> product_s: order has been paid
    product_s -->> order_s: product(s) reserved
    order_s -->> customer: order approved
```
