#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Handle Dynamic WebTable

  @tag1
  Scenario: Dynamic WebTable Practice 1
    Given I am on Automation Practice WebPage
    When I click on "India"
    Then I get capital,currency & primary Languages of "India"

  #@tag2
  #Scenario Outline: Dynamic WebTable Practice <count>
    #Given I am on Automation Practice WebPage
    #When I click on "<country>"
    #Then I get capital,currency & primary Languages of "<country>"
#
    #Examples:
      #| count  |         country 					|
#			|  2	   | India									  |
#			|  3	   | Canada									  |
#			|  4	   | Qatar									  |
#			|  5	   | Germany								  |
#			|  6	   | United States of America |
			
			