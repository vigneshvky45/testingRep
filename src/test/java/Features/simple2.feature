Feature: Test simple web launch

Scenario: Verify to launch simple site with home
Given I am logged in as "Manager_Home"
Then I will see "Home"
#But I will not see "Username"
When I navigate to "Students>Students"
Then I will see "Keywords"
And I search with "AAAStudent"
And I impersonateAs "AAAStudent"
#And I click "AAAStudent" Link
#And I open "Login As" tab
