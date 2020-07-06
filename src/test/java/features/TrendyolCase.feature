Feature: Trendyol Test Automation Engineer Case

  @clear_basket @log_out
  Scenario: Login to the system, check the categories images and add product to cart
    Given I visit "https://www.trendyol.com/"
    When I close the advertising popup if it is displayed
    And I opened the login popup
    And I logged in with following credentials
    |kaanmertcakmak@hotmail.com|Test1234|
    Then I should be redirected to "trendyol.com/butik/liste/erkek?e=login"
    And I closed Indirimleri Kacirma Popup
    Then I check if all images are displaying properly in following categories
    |KADIN|ÇOCUK|EV & YAŞAM|SÜPERMARKET|KOZMETİK|AYAKKABI & ÇANTA|SAAT & AKSESUAR|ELEKTRONİK|
    And I visit one of the butik Pages
    Then I verified if all product images are loaded
    And I navigated one of the product detail pages
    And I add product to the cart
    Then I verified product is added to basket properly
