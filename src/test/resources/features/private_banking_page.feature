Feature: Просмотр страницы Private Banking

  Scenario: Успешный переход на страницу 'Private Banking'
    Given пользователь находится на главной странице
    When он нажимает на пункт меню "Private Banking"
    Then открывается страница "Private Banking"