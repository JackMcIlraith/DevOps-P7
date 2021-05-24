Feature: Crypto Exchange
  This feature describes various scenarios for users trading crypto from their revolut account

  #As a user, I can use my revolute account to by/sell crypto

  Scenario Outline: Add money to Bitcoin account using revolut account
    Given Charles has <startingBalance> euro in his euro Revolut account
    And Charles has <startingCrypto> bitcoin in his bitcoin crypto Revolut account
    And Charles selects <exchangeAmountInEuro> euro as the exchange amount
    And Bitcoins exchange rate is <exchangeRate>
    And Charles has sufficient funds in Euro
    When Charles exchanges for bitcoin
    Then Charles new balance of his euro account should now be <euroBalance>
    And Charles new balance of his bitcoin crypto account should now be <cryptoBalance>
    Examples:
      | startingBalance | startingCrypto | exchangeAmountInEuro | exchangeRate | euroBalance | cryptoBalance |
      | 101             | 0              | 100                  | 0.000037     | 1           | 0.0037        |
      | 101             | 0              | 10                   | 0.000037     | 91          | 0.00037       |
      | 101             | 0              | 1                    | 0.000037     | 100         | 0.000037      |
      | 500             | 1              | 500                  | 0.000037     | 0           | 1.0185        |

  Scenario Outline: Add money to Bitcoin account using revolut account, fails ue to insufficient funds
    Given Charles has <startingBalance> euro in his euro Revolut account
    And Charles has <startingCrypto> bitcoin in his bitcoin crypto Revolut account
    And Charles selects <exchangeAmountInEuro> euro as the exchange amount
    And Bitcoins exchange rate is <exchangeRate>
    And Charles has insufficient funds in Euro Account, is rejected by payment service
    When Charles exchanges for bitcoin
    Then Charles new balance of his euro account should now be <euroBalance>
    And Charles new balance of his bitcoin crypto account should now be <cryptoBalance>
    Examples:
      | startingBalance | startingCrypto | exchangeAmountInEuro | exchangeRate | euroBalance | cryptoBalance |
      | 101             | 101            | 1000                 | 0.000037     | 101         | 101           |
      | 100             | 101            | 100.1                | 0.000037     | 100         | 101           |

  Scenario Outline: Sell Bitcoin and deposit Euro into Eur Account - sufficient bitcoin
    Given Charles has <startingBalance> euro in his euro Revolut account
    And Charles has <startingCrypto> bitcoin in his bitcoin crypto Revolut account
    And Charles selects <exchangeAmountInBitcoin> bitcoin as the exchange amount
    And Bitcoins exchange rate is <exchangeRate>
    And Charles has sufficient funds in Bitcoin
    When Charles exchanges for euro
    Then Charles new balance of his euro account should now be <euroBalance>
    And Charles new balance of his bitcoin crypto account should now be <cryptoBalance>
    Examples:
      | startingBalance | startingCrypto | exchangeAmountInBitcoin | exchangeRate | euroBalance | cryptoBalance |
      | 0               | 1              | 1                       | 0.000037     | 27027.03    | 0             |
      | 100             | 10             | 1                       | 0.000037     | 27127.03    | 9             |
      | 0               | 0              | 0                       | 0.000037     | 0           | 0             |
      | 0               | 10             | 10                      | 0.000037     | 270270.27   | 0             |

  Scenario Outline: Sell Bitcoin and deposit Euro into Eur Account - insufficient bitcoin
    Given Charles has <startingBalance> euro in his euro Revolut account
    And Charles has <startingCrypto> bitcoin in his bitcoin crypto Revolut account
    And Charles selects <exchangeAmountInBitcoin> bitcoin as the exchange amount
    And Bitcoins exchange rate is <exchangeRate>
    And Charles has insufficient funds in Bitcoin Account, is rejected by payment service
    When Charles exchanges for euro
    Then Charles new balance of his euro account should now be <euroBalance>
    And Charles new balance of his bitcoin crypto account should now be <cryptoBalance>
    Examples:
      | startingBalance | startingCrypto | exchangeAmountInBitcoin | exchangeRate | euroBalance | cryptoBalance |
      | 0               | 1              | 10                      | 0.000037     | 0           | 1             |
      | 0               | 0              | 0.0001                  | 0.000037     | 0           | 0             |
