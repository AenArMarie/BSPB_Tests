Feature: Api запросы

  @smoke
  Scenario: Получение офисов обмена валюты

    Then коллекция по запросу соответствует требованиям

  @smoke
  Scenario: Проверка наличия имен в ответе на API-запрос с получением офисом обмена валюты

    Then коллекция по запросу офисов обмена валюты содержит участки с именами:
      | ДО "Гаванский"  |
      | ДО "Пушкинский" |
      | ДО "Тосненский" |