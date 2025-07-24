@smoke @web
Feature: Просмотр страниц верхнего горизонтального меню

  Scenario Outline: Успешный переход на страницу <pageName>
    Given пользователь находится на главной странице
    When пользователь нажимает на пункт меню <pageName>
    Then открывается страница <pageName>
    Examples:
      | pageName           |
      | "Бизнесу"          |
      | "Финансовые рынки" |
      | "Инвесторам"       |
      | "ВЭД"              |
      | "Private Banking"  |