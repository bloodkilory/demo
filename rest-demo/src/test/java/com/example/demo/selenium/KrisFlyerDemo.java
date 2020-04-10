package com.example.demo.selenium;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.beust.jcommander.internal.Maps;

/**
 * @author yangkun
 * generate on 2017/6/29
 */
public class KrisFlyerDemo {

    private WebDriver webDriver;
    private JavascriptExecutor jsExecutor;

    //    @Before
    public void login() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/bloodkilory/Downloads/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.singaporeair.com/zh_CN/cn/home#kflogin");
        System.out.println(webDriver.getTitle());

        WebElement card_number = webDriver.findElement(By.xpath("//*[@id=\"membership-1\"]"));
        WebElement pass = webDriver.findElement(By.xpath("//*[@id=\"membership-2\"]"));
        WebElement login_button = webDriver.findElement(By.xpath("//*[@id=\"submit-1\"]"));

        card_number.sendKeys("8829705779");
        TimeUnit.SECONDS.sleep(1);
        pass.sendKeys("890814");
        TimeUnit.SECONDS.sleep(1);
        login_button.click();
    }

    @Before
    public void noLogin() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/bloodkilory/Downloads/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.singaporeair.com/zh_CN/cn/home");
        System.out.println(webDriver.getTitle());
    }

    @Test
    public void test() throws Exception {
//        WebElement book_link = waitforLogin(By.xpath("//*[@id=\"container\"]/div[2]/div[1]/div[2]/div[2]/ul/li[2]/a"));
//        book_link.click();
        jsExecutor = (JavascriptExecutor) webDriver;

        //日期
        String type = "往返";
        String dep_date = "2017-08-25";//dd/MM/yyyy
        String ret_date = "2017-11-15";//dd/MM/yyyy
        setDates(type, dep_date, ret_date);

        String city_in = "sin";
        String city_in2 = "PEK";
        setCityFromAndCityTo(city_in, city_in2);

        //舱位等级
//        String cabinClass = "economy";
//        String cabinClass = "premiumeconomy";
//        String cabinClass = "business";
        String cabinClass = "firstSuite";
        setCabinClass(cabinClass);

        int adult_num = 2;
        int child_num = 1;
        int baby_num = 0;
        setPassengerNum(adult_num, child_num, baby_num);

        WebElement submit = webDriver.findElement(By.xpath("//*[@id=\"city-travel-input-2\"]"));
        submit.click();

        chooseFlight();
        passengerInfo("Mr", "Yang", "Kim");

        WebElement alert = null;
        try {
            alert = webDriver.findElement(By.cssSelector("alert-block checkin-alert error-alert"));
        } catch(Exception e) {
            //
        }
        if(alert != null) {
            throw new RuntimeException("");
        }
    }

    /**
     * 2.选择航班
     */
    private void chooseFlight() {
        WebElement next_step = waitforElement(By.xpath("//*[@id=\"btn-next\"]"));
        next_step.click();
    }

    /**
     * 3.填写登记用户信息
     *
     * @param title
     */
    private void passengerInfo(String title, String firstName, String lastName) {
        //title
        WebElement titleUl = waitforElement(By.xpath("//*[@id=\"customSelect-0-listbox\"]"));
        Select titleSelect = new Select(waitforElement(By.xpath("//*[@id=\"title-name-last\"]")));
        List<WebElement> titleLis = titleUl.findElements(By.tagName("li"));
        for(WebElement li : titleLis) {
            String value = li.getAttribute("data-value");
            if(value.equals("")) {
                jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'false')", li);
                jsExecutor.executeScript("arguments[0].className=''", li);
            }
            if(value.equals(title)) {
                jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'true')", li);
                jsExecutor.executeScript("arguments[0].className='active'", li);
                break;
            }
        }
        List<WebElement> titleOptions = titleSelect.getOptions();
        for(WebElement option : titleOptions) {
            String value = option.getAttribute("value");
            if(value.equals("")) {
                jsExecutor.executeScript("arguments[0].selected = false", option);
            }
            if(value.equals(title)) {
                jsExecutor.executeScript("arguments[0].selected = true", option);
                break;
            }
        }

        //name
        WebElement lastNameInput = waitforElement(By.xpath("//*[@id=\"first-name-last\"]"));
        WebElement firstNameInput = waitforElement(By.xpath("//*[@id=\"last-name-second\"]"));
        lastNameInput.sendKeys(lastName);
        firstNameInput.sendKeys(firstName);

    }

    private void setDates(String type, String dep, String ret) {
        Map<String, String> dateMap = parseDate(dep, ret);
        String dep_month = dateMap.get("dep_month");
        String dep_day = dateMap.get("dep_day");
        String dep_year = dateMap.get("dep_year");
        String ret_month = dateMap.get("ret_month");
        String ret_day = dateMap.get("ret_day");
        String ret_year = dateMap.get("ret_year");
        WebElement one_way = webDriver.findElement(By.xpath("//*[@id=\"city-radio-5\"]"));
        WebElement two_way = webDriver.findElement(By.xpath("//*[@id=\"city-radio-4\"]"));
        WebElement div_one_way1 = webDriver.findElement(By.xpath("//*[@id=\"city-one-way-1\"]"));
        WebElement div_one_way2 = webDriver.findElement(By.xpath("//*[@id=\"city-one-way-2\"]"));
        //form-group grid-row hidden
        if(type.contains("单")) {
            jsExecutor.executeScript("arguments[0].removeAttribute('checked')", two_way);
            jsExecutor.executeScript("arguments[0].setAttribute('checked',true)", one_way);
            jsExecutor.executeScript("arguments[0].className='form-group grid-row hidden'", div_one_way1);
            jsExecutor.executeScript("arguments[0].className='form-group grid-row'", div_one_way2);
            WebElement div_start_day2 = webDriver.findElement(By.xpath("//*[@id=\"city-travel-start-day-2\"]"));
            div_start_day2.click(); //调出日期选择器表格
            // 根据年、月定位表格
            WebElement tbody1 = getYear(dep_year, dep_month);
            WebElement td = findDatePickerTd(dep_day, tbody1);
            jsExecutor.executeScript("arguments[0].className=' ui-datepicker-days-cell-over  ui-datepicker-current-day'", td);
            jsExecutor.executeScript("arguments[0].className='ui-state-default ui-state-active'", td.findElement(By.tagName("a")));
            td.click();
        } else {
            WebElement div_start_day = waitforElement(By.xpath("//*[@id=\"city-travel-start-day\"]"));
            div_start_day.click(); //调出日期选择器表格
            this.sleep();
            WebElement tbody1 = getYear(dep_year, dep_month);
            WebElement td = findDatePickerTd(dep_day, tbody1);
            jsExecutor.executeScript("arguments[0].className=' ui-datepicker-days-cell-over  ui-datepicker-current-day'", td);
            jsExecutor.executeScript("arguments[0].className='ui-state-default ui-state-active'", td.findElement(By.tagName("a")));
            td.click();

            WebElement div_return_day = waitforElement(By.xpath("//*[@id=\"city-travel-return-day\"]"));
            div_return_day.click(); //调出日期选择器表格//*[@id="ui-datepicker-div"]/div[2]/div/a
            this.sleep();
            WebElement tbody2 = getYear(ret_year, ret_month);
            WebElement td2 = findDatePickerTd(ret_day, tbody2);
            jsExecutor.executeScript("arguments[0].className=' ui-datepicker-days-cell-over  ui-datepicker-current-day'", td2);
            jsExecutor.executeScript("arguments[0].className='ui-state-default ui-state-active'", td2.findElement(By.tagName("a")));
            td2.click();
        }
    }

    private void setCityFromAndCityTo(String cityFrom, String cityTo) {
        WebElement city_from = webDriver.findElement(By.xpath("//*[@id=\"city-1\"]"));
        Select select_flight1 = new Select(webDriver.findElement(By.xpath("//*[@id=\"cib-flight1\"]")));
        List<WebElement> selects1 = select_flight1.getOptions();
        WebElement origin = webDriver.findElement(By.id("originCityCode"));
        boolean fli1 = false;
        for(WebElement element : selects1) {
            String value = element.getAttribute("value");
            if(value.equalsIgnoreCase(cityFrom)) {
                city_from.clear();
                city_from.sendKeys(value);
                jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", origin);
                fli1 = true;
                break;
            }
        }

        WebElement city_to = webDriver.findElement(By.xpath("//*[@id=\"city-2\"]"));
        Select select_flight2 = new Select(webDriver.findElement(By.xpath("//*[@id=\"cib-flight2\"]")));
        List<WebElement> selects2 = select_flight2.getOptions();
        WebElement dest = webDriver.findElement(By.xpath("//*[@id=\"destCityCode\"]"));
        boolean fli2 = false;
        for(WebElement element : selects2) {
            String value = element.getAttribute("value");
            if(value.equalsIgnoreCase(cityTo)) {
                city_to.clear();
                city_to.sendKeys(value);
                jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", dest);
                fli2 = true;
                break;
            }
        }
        if(!fli1) throw new RuntimeException("未查询到出发地");
        if(!fli2) throw new RuntimeException("未查询到目的地");
    }

    private void setCabinClass(String cabinClass) {
        WebElement cabinUlDiv = waitforElement(By.xpath("/html/body/div[9]"));
        jsExecutor.executeScript("arguments[0].className='display: none; width: 647px; position: absolute; z-index: 1000; top:" +
                " 542px; left: 20px;'", cabinUlDiv);
        jsExecutor.executeScript("arguments[0].className='display: block; width: 647px; position: absolute; z-index: 1000; top: 542px; left: 20px;'", cabinUlDiv);
        WebElement cabinClassUl = webDriver.findElement(By.xpath("//*[@id=\"customSelect-0-listbox\"]"));
        List<WebElement> lis = cabinClassUl.findElements(By.tagName("li"));
        for(WebElement li : lis) {
            String value = li.getAttribute("data-value");
            if(value.equals("economy")) { // 取消默认
                jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'false')", li);
                jsExecutor.executeScript("arguments[0].className=''", li);
            }
            if(value.equals(cabinClass)) {
                WebElement cib = webDriver.findElement(By.xpath("//*[@id=\"cabinCIB\"]"));
                String ca = convertCabinClass(cabinClass);
                jsExecutor.executeScript("arguments[0].setAttribute('value', '" + ca + "')", cib);
                jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'true')", li);
                jsExecutor.executeScript("arguments[0].className='active'", li);
                break;
            }
        }
    }

    private String convertCabinClass(String ori) {
        switch(ori) {
            case "economy":
                return "Y";
            case "premiumeconomy":
                return "S";
            case "business":
                return "J";
            case "firstSuite":
                return "F";
        }
        throw new RuntimeException("Error Cabin Class");
    }

    private WebElement getYear(String yearStr, String monthStr) {
        WebElement next = waitforElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/a"));
        WebElement firstYear = waitforElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[2]"));
        WebElement secondYear = waitforElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/div/span[2]"));
        String first = firstYear.getText();
        String second = secondYear.getText();
        if(first.equals(second)) {
            if(yearStr.equals(first)) {
                return getMonth(monthConvert(monthStr));
            } else {
                next.click();
                this.sleep();
                return getMonth(monthConvert(monthStr));
            }
        } else {
            if(yearStr.equals(first) || yearStr.equals(second)) {
                return getMonth(monthConvert(monthStr));
            } else {
                next.click();
                this.sleep();
                return getMonth(monthConvert(monthStr));
            }
        }
    }

    private WebElement getMonth(String monthStr) {
        WebElement next = waitforElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/a"));
        WebElement month1 = waitforElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[1]"));
        WebElement month2 = waitforElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/div/span[1]"));
        String mon1 = month1.getText();
        String mon2 = month2.getText();
        if(mon1.equals(monthStr)) {
            return waitforElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody"));
        } else if(mon2.equals(monthStr)) {
            return waitforElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/table/tbody"));
        } else {
            next.click();
            this.sleep();
            return getMonth(monthStr);
        }
    }

    private void setPassengerNum(int adult_num, int child_num, int baby_num) {
        WebElement adultUl = webDriver.findElement(By.xpath("//*[@id=\"customSelect-1-listbox\"]"));
        WebElement childUl = webDriver.findElement(By.xpath("//*[@id=\"customSelect-2-listbox\"]"));
        WebElement babyUl = webDriver.findElement(By.xpath("//*[@id=\"customSelect-3-listbox\"]"));
        WebElement adult = webDriver.findElement(By.xpath
                ("//*[@id=\"form-book-travel\"]/fieldset/div[2]/div/div[5]/div[2]/div[1]/div/div/span[1]"));
        Select adultSelect = new Select(webDriver.findElement(By.xpath("//*[@id=\"city-cabin-2\"]")));
        if(adult_num > 1) {
            jsExecutor.executeScript("arguments[0].innerHTML='" + adult_num + "'", adult);
            List<WebElement> adultLis = adultUl.findElements(By.tagName("li"));
            for(WebElement li : adultLis) {
                String value = li.getAttribute("data-value");
                if(value.equals("1")) {
                    jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'false')", li);
                    jsExecutor.executeScript("arguments[0].className=''", li);
                }
                if(value.equals(String.valueOf(adult_num))) {
                    jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'true')", li);
                    jsExecutor.executeScript("arguments[0].className='active'", li);
                    break;
                }
            }
            List<WebElement> options = adultSelect.getOptions();
            for(WebElement option : options) {
                String value = option.getAttribute("value");
                if(value.equals("1")) {
                    jsExecutor.executeScript("arguments[0].selected = false", option);
                }
                if(value.equals(String.valueOf(adult_num))) {
                    jsExecutor.executeScript("arguments[0].selected = true", option);
                    break;
                }
            }
        }
        WebElement child = webDriver.findElement(By.xpath
                ("//*[@id=\"form-book-travel\"]/fieldset/div[2]/div/div[5]/div[2]/div[2]/div/div/span[1]"));
        Select childSelect = new Select(webDriver.findElement(By.xpath("//*[@id=\"city-cabin-3\"]")));
        if(child_num > 0) {
            jsExecutor.executeScript("arguments[0].innerHTML='" + child_num + "'", child);
            List<WebElement> childLis = childUl.findElements(By.tagName("li"));
            for(WebElement li : childLis) {
                String value = li.getAttribute("data-value");
                if(value.equals("0")) {
                    jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'false')", li);
                    jsExecutor.executeScript("arguments[0].className=''", li);
                }
                if(value.equals(String.valueOf(child_num))) {
                    jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'true')", li);
                    jsExecutor.executeScript("arguments[0].className='active'", li);
                    break;
                }
            }
            List<WebElement> options = childSelect.getOptions();
            for(WebElement option : options) {
                String value = option.getAttribute("value");
                if(value.equals("0")) {
                    jsExecutor.executeScript("arguments[0].selected = false", option);
                }
                if(value.equals(String.valueOf(child_num))) {
                    jsExecutor.executeScript("arguments[0].selected = true", option);
                    break;
                }
            }
        }
        WebElement baby = webDriver.findElement(By.xpath
                ("//*[@id=\"form-book-travel\"]/fieldset/div[2]/div/div[5]/div[2]/div[3]/div/div/span[1]"));
        Select babySelect = new Select(webDriver.findElement(By.xpath("//*[@id=\"city-cabin-4\"]")));
        if(baby_num > 0) {
            jsExecutor.executeScript("arguments[0].innerHTML='" + baby_num + "'", baby);
            List<WebElement> babyLis = babyUl.findElements(By.tagName("li"));
            for(WebElement li : babyLis) {
                String value = li.getAttribute("data-value");
                if(value.equals("0")) {
                    jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'false')", li);
                    jsExecutor.executeScript("arguments[0].className=''", li);
                }
                if(value.equals(String.valueOf(baby_num))) {
                    jsExecutor.executeScript("arguments[0].setAttribute('aria-selected', 'true')", li);
                    jsExecutor.executeScript("arguments[0].className='active'", li);
                    break;
                }
            }
            List<WebElement> options = babySelect.getOptions();
            for(WebElement option : options) {
                String value = option.getAttribute("value");
                if(value.equals("0")) {
                    jsExecutor.executeScript("arguments[0].selected = false", option);
                }
                if(value.equals(String.valueOf(baby_num))) {
                    jsExecutor.executeScript("arguments[0].selected = true", option);
                    break;
                }
            }
        }
    }

    private void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch(Exception ex) {
            //
        }
    }

    //默认2017-08-09
    private Map<String, String> parseDate(String dep, String ret) {
        Map<String, String> dateMap = Maps.newHashMap();
        String[] depArr = dep.split("-");
        String[] retArr = ret.split("-");
        dateMap.put("dep_year", depArr[0]);
        dateMap.put("dep_month", depArr[1]);
        dateMap.put("dep_day", depArr[2]);
        dateMap.put("ret_year", retArr[0]);
        dateMap.put("ret_month", retArr[1]);
        dateMap.put("ret_day", retArr[2]);
        return dateMap;
    }

    private WebElement findDatePickerTd(String content, WebElement table) {
        List<WebElement> trs = table.findElements(By.tagName("tr"));
        for(WebElement tr : trs) {
            List<WebElement> tds = tr.findElements(By.tagName("td"));
            for(WebElement td : tds) {
                WebElement a = null;
                try {
                    a = td.findElement(By.tagName("a"));
                } catch(Exception ex) {
                    //
                }

                if(a != null) {
                    String s = a.getText();
                    if(content.equals(s)) {
                        return td;
                    }
                }
            }
        }
        throw new RuntimeException("No Td Element!");
    }

    private WebElement waitforLogin(By by) {
        int retry = 3;
        WebElement login = null;
        while(retry > 0) {
            try {
                login = webDriver.findElement(by);
                break;
            } catch(Exception ex) {
                retry--;
                try {
                    TimeUnit.SECONDS.sleep(3);
//                    WebElement cap = webDriver.findElement(By.cssSelector("captchaHidden"));
//                    WebElement capInput = webDriver.findElement(By.xpath("//*[@id=\"recaptcha_response_field\"]"));
//                    WebElement img = webDriver.findElement(By.xpath("//*[@id=\"recaptcha_challenge_image\"]"));
//                    WebElement submit = webDriver.findElement(By.xpath("//*[@id="captchaSubmit"]"]"));
//                    String src = img.getAttribute("src");
                    //todo Send img to WuHan
//                    capInput.clear();
//                    capInput.sendKeys(getCapContent(src));
                    //submit.click();
                } catch(Exception e) {
                    //
                }
            }
        }
        if(login != null) {
            return login;
        } else {
            throw new RuntimeException();
        }
    }

    private WebElement waitforElement(By by) {
        int retry = 3;
        WebElement element = null;
        while(retry > 0) {
            try {
                element = webDriver.findElement(by);
                break;
            } catch(Throwable ex) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    retry--;
                } catch(Exception e) {
                    //
                }
            }
        }
        if(element != null) {
            return element;
        } else {
            throw new RuntimeException();
        }
    }

    private String getCapContent(String src) {
        return "";
    }

    private String monthConvert(String monthNum) {
        switch(monthNum) {
            case "01":
                return "一月";
            case "02":
                return "二月";
            case "03":
                return "三月";
            case "04":
                return "四月";
            case "05":
                return "五月";
            case "06":
                return "六月";
            case "07":
                return "七月";
            case "08":
                return "八月";
            case "09":
                return "九月";
            case "10":
                return "十月";
            case "11":
                return "十一月";
            case "12":
                return "十二月";
        }
        throw new RuntimeException("Error Month Num!");
    }

}
