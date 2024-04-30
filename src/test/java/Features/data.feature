Feature: date check

Scenario: First verification
Given I click "Interactions with simple elements" Link
#And I fill fields
  #| field    | value | type | Optional |
  #| Email | vigneshvky45@gmail.com | Textbox | y | 
#	| Password | vky@123 | Textbox | y |
#When I click "Sign in" button
When I click "About" Link
Then I will see "We elevate the way the world creates technology!"