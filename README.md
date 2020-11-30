# TDD Kata: Bake Sale

Language: Scala

## Description

The boy scouts are preparing to go for a camping trip, and they are trying to raise some funds with a bake sale. 

There are four items they would like to sell on this sale with specific prices and quantities of each:

|            | Price | Qty |
|------------|-------|-----|
| (B)rownie  | $0.65 |  48 |
| (M)uffin   | $1.00 |  36 |
| (C)ake Pop | $1.35 |  24 |
| (W)ater    | $1.50 |  30 |

* The application must calculate the smallest amount of change to give a person if they overpay.
* If you do not have stock of an item, you cannot make the sale.
* Purchases are input as comma delimited string. The first letter of each item is its input code, B for Brownie, M for
Muffin, C for Cake Pop and W for Water.
* The application then responds with a total if there are enough of each item. Otherwise responds with “Not enough stock.”
* If the total appears the user, then inputs the amount paid. If the amount is equal to or greater than the amount due
the application, then figures out the amount of change to give. If the amount is less than the amount due it responds 
with “Not enough money.”

Examples
```
Items to Purchase > B, C, W
Total > $3.50
Amount Paid > $4.00
Change > $0.50
```

```
Items to Purchase > B
Total > $0.65
Amount Paid > $0.75
Change > $0.10
```

```
Items to Purchase > C,M
Total > $2.35
Amount Paid > $2.00
Change > Not enough money
```

```
Items to Purchase > W
Total > Not enough stock
```

### Extra credit

* Specify which is the requested product that does not have stock when stock is out. 
```
Items to Purchase > W,B,C
Total > Not enough stock of: Water, Brownie

```
* Read quantities of products when asking which products to purchase
```
Items to Purchase > 3B, 2C, 5W
Total > $12.15
Amount Paid > $15.00
Change > $2.85
```
* Read the configuration from an input file when starting the application
