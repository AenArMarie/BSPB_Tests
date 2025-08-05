Feature: Конвертация валют

  @regression @web
  Scenario Outline: Проверка курса при малом и большом объеме валюты
    Given пользователь находится на главной странице
    And пользователь открывает форму конвертации валют
    And выбрана исходная валюта с текстом <existingCurrency>
    And выбрана целевая валюта с текстом <convertedCurrency>

    When пользователь вводит количество валюты <value>
    Then вычисленное значение равно конверсии количества валюты <value> по курсу для <conversionText> с погрешностью <marginOfError>

    Examples:
      | existingCurrency | convertedCurrency  | conversionText | value | marginOfError |
      | "Доллар США"     | "Российский рубль" | "1 USD"        | 499.0 | 0.1           |
      | "Доллар США"     | "Российский рубль" | "500 USD"      | 501.0 | 0.1           |