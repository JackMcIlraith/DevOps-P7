Feature: Crypto Exchange
  This feature describes various scenarios for users trading crypto from their revolut account

  #As a user, I can use my revolute account to by/sell crypto

  Scenario: Add money to Bitcoin account using revolut account
    Given Charles has 101 euro in his euro Revolut account
    And Charles has 0 bitcoin in his bitcoin crypto Revolut account
    And Charles selects 100 euro as the exchange amount
    #And  Danny selects his BankAccount as his topUp method
    When Charles exchanges for bitcoin
    Then Charles new balance of his euro account should now be 1
    And Charles new balance of his bitcoin crypto account should now be 100