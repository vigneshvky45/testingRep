Feature: Buy products and validating cart 

Scenario: Placing the sample order to specific location
Given Lanching greenkart site on "chrome"
And I am adding "Brocolli" to the cart
And I am adding "Cucumber" to the cart
And I am adding "Cauliflower" to the cart
And click the "Cart" icon
When I am clicking the "PROCEED TO CHECKOUT" button
Then I will see the below details
| Product Name | Quantiry | Price | Total |
| Brocolli - 1 Kg | 1 | 130 | 120 |
| Cauliflower - 1 Kg | 1 | 60 | 60 |

Scenario: Placing the sample order to specific location
Given Lanching greenkart site on "firefox"
And I am adding "Brocolli" to the cart
And I am adding "Cauliflower" to the cart
And click the "Cart" icon
When I am clicking the "PROCEED TO CHECKOUT" button
Then I will see the below details
| Product Name | Quantiry | Price | Total |
| Brocolli - 1 Kg | 1 | 120 | 120 |
| Cauliflower - 1 Kg | 1 | 60 | 60 |