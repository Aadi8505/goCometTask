# CaseKaro Test Automation Framework

This project is a high-performance, robust, and clean test automation framework written in **Java** using **Playwright Java** and **Cucumber JVM** (BDD). It is designed to automate and assert the end-to-end shopping flow on the Shopify-based website **CaseKaro** (https://casekaro.com/).

---

## 🚀 Key Features

* **Headed & Headless Execution**: Watch the tests run visually in real-time or execute silently on CI servers via simple system properties.
* **Negative Brand Assertions**: Dynamically validates autocomplete search suggestions to ensure only relevant models matching the keyword are shown.
* **Smart Autocomplete Interactions**: Wait for suggestions to render and pick exact matching models (e.g. specifically clicking "iPhone 16 Pro" while ignoring "iPhone 16 Pro Max").
* **Variant Add-To-Cart Loop**: Selects and adds multiple product material variants (**Hard**, **Soft**, **Glass**) to the shopping cart, handling Shopify cart drawers dynamically.
* **Console Reporting**: Extract product details (Material, Price, Product Link) for each item in the cart and prints them beautifully to the console.
* **No `try-catch` blocks**: Adheres to strict test engineering standards to allow natural framework failures and cleaner traces.
* **No global Maven required**: Includes a pre-configured portable Maven executable in the project workspace to allow running the suite out of the box.

---

## 📂 Project Architecture & Directory Structure

```
goCometTask/
├── pom.xml                                    # Maven Dependencies & Configuration
├── README.md                                  # Complete Workspace documentation
├── tools/
│   └── apache-maven-3.9.9/                    # Portable Maven distribution
└── src/
    └── test/
        ├── java/
        │   └── com/
        │       └── casekaro/
        │           ├── hooks/
        │           │   └── Hooks.java         # Lifecycle, browser startup and teardown
        │           ├── runners/
        │           │   └── TestRunner.java    # JUnit 4 & Cucumber Configuration
        │           └── steps/
        │               └── CaseKaroSteps.java # Playwright actions & Step Definitions
        └── resources/
            └── features/
                └── casekaro.feature           # Cucumber Gherkin Scenarios
```

---

## 📝 Codebase Detailed Explanation

### 1. [`pom.xml`](file:///c:/Users/HP%20VICTUS/Desktop/goCometTask/pom.xml)
This Maven descriptor manages dependencies:
* `microsoft-playwright` (v1.44.0): For fast, auto-waiting, and robust browser interactions.
* `cucumber-java` & `cucumber-junit` (v7.18.0): Cucumber integration with JUnit.
* `junit` (v4.13.2): Core test execution engine.
* Configured using **Java 21** compiler targets.

### 2. [`casekaro.feature`](file:///c:/Users/HP%20VICTUS/Desktop/goCometTask/src/test/resources/features/casekaro.feature)
Written in **Gherkin**, this feature file outlines the behavior of the shopping scenario from navigation and negative search validation to multi-variant cart addition and price extraction.

### 3. [`Hooks.java`](file:///c:/Users/HP%20VICTUS/Desktop/goCometTask/src/test/java/com/casekaro/hooks/Hooks.java)
Handles Playwright browser lifecycle:
* `@Before` setup: Launches Playwright Chromium with browser parameters.
* **Headed Toggling**: Configures headless mode based on `System.getProperty("headless", "false")`.
* **SlowMo Delay**: Configured with a `800ms` SlowMo delay to make the execution smooth and extremely easy to watch and verify.
* `@After` teardown: Gracefully closes the Page, Context, Browser, and Playwright processes to prevent residual process leaks.

### 4. [`TestRunner.java`](file:///c:/Users/HP%20VICTUS/Desktop/goCometTask/src/test/java/com/casekaro/runners/TestRunner.java)
Bridges JUnit and Cucumber. Set up with `@CucumberOptions` targeting the features path, step definition glue classes, and plugins for beautiful `pretty`, `html:target/cucumber-reports.html`, and `json` outputs.

### 5. [`CaseKaroSteps.java`](file:///c:/Users/HP%20VICTUS/Desktop/goCometTask/src/test/java/com/casekaro/steps/CaseKaroSteps.java)
Core implementation containing the Playwright interactions:
* **Navigation & Mobile Covers click**: Handles mobile responsive hamburger menus gracefully, with automatic direct navigation fallback to `/pages/phone-cases-by-model` if top links are covered.
* **Negative Validation**: Collects visible suggestion elements and asserts none contain other brands (Samsung, OnePlus, Oppo, etc.) when Apple is typed.
* **Autocomplete Selection**: Filters search dropdown options to specifically click the exact item matching "iPhone 16 Pro" while bypassing other matching items.
* **Product Material Selection**: Selects variants using either select dropdowns or buttons/radio elements depending on the Shopify theme layout.
* **Shopify Drawer Handling**: Closes the shopping cart drawers/slideouts by pressing `Escape` or clicking `Close` between variant selections.
* **Details Printing**: Iterates over cart items to extract and print details: Variant Material, Price, and direct link.

---

## 🛠️ Setup & Pre-requisites

1. **Java Development Kit (JDK 21)**: Ensure Java 21 is installed. (Verify using `java -version`).
2. **Apache Maven**: Since a global Maven is not required, the project contains a portable Maven located under `.\tools\apache-maven-3.9.9\bin\mvn.cmd`.

---

## 🚀 How to Run Tests

Open your terminal in the project root (`c:\Users\HP VICTUS\Desktop\goCometTask`) and execute one of the following commands:

### A. Run in Visual (Headed) Mode [Recommended]
To watch the browser perform all actions visually:
```powershell
.\tools\apache-maven-3.9.9\bin\mvn.cmd clean test
```

### B. Run in Headless (Silent) Mode
To run the tests silently in the background:
```powershell
.\tools\apache-maven-3.9.9\bin\mvn.cmd clean test -Dheadless=true
```

---

## 📊 Verifying Execution & Reports

Upon test completion, the results can be verified in multiple ways:

1. **Console Output**: Look at your terminal console logs to view the printed list of items, materials, prices, and direct links:
   ```
   ========================================================
               CASEKARO CART PRODUCTS & PRICES
   ========================================================
   Product Item #1:
     - Material Variant: Hard
     - Price:            Rs. 499.00
     - Product Link:     https://casekaro.com/products/iphone-16-pro-back-covers-hard
   --------------------------------------------------------
   ...
   ========================================================
   ```
2. **Cucumber HTML Report**: Open the beautifully formatted HTML report in your browser:
   * File path: [cucumber-reports.html](file:///c:/Users/HP%20VICTUS/Desktop/goCometTask/target/cucumber-reports.html)
