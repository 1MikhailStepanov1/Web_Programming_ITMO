<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList"%>
<%@ page import="utils.Data"%>
<%@ page import="java.util.Iterator" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Lab2</title>
    <link rel="stylesheet" href="styles/main.css">
    <link rel="icon" href="img/favicon.ico">
    <script src="js/jquery-3.6.0.js"></script>
    <script src="js/draw_table.js"></script>
    <script src="js/draw_shot.js"></script>
</head>
<body>
<header class="header">
    <span id="num1">Student: Stepanov M.A. Group: P3231</span>
    <span id="num2">Variant: 52062</span>
</header>
<main class="main">
    <section class="user_input">
        <svg id="svg" class="svg" height="400" width="400" xmlns="http://www.w3.org/2000/svg">
            <!--- Coordinate axes --->
            <line stroke="black" x1="0" x2="400" y1="200" y2="200"></line>
            <line stroke="black" x1="200" x2="200" y1="0" y2="400"></line>
            <!--- Axis directions --->
            <polygon fill="black" stroke="black" points="200,0 195,10 205,10"></polygon>
            <polygon fill="black" stroke="black" points="400,200 390,195 390,205"></polygon>
            <!--- Segments --->
            <line stroke="black" x1="50" x2="50" y1="195" y2="205"></line>
            <line stroke="black" x1="125" x2="125" y1="195" y2="205"></line>
            <line stroke="black" x1="275" x2="275" y1="195" y2="205"></line>
            <line stroke="black" x1="350" x2="350" y1="195" y2="205"></line>
            <line stroke="black" x1="195" x2="205" y1="50" y2="50"></line>
            <line stroke="black" x1="195" x2="205" y1="125" y2="125"></line>
            <line stroke="black" x1="195" x2="205" y1="275" y2="275"></line>
            <line stroke="black" x1="195" x2="205" y1="350" y2="350"></line>
            <!--- Units --->
            <text fill="black" x="40" y="190">-R</text>
            <text fill="black" x="110" y="190">-R/2</text>
            <text fill="black" x="265" y="190">R/2</text>
            <text fill="black" x="345" y="190">R</text>
            <text fill="black" x="210" y="54">R</text>
            <text fill="black" x="210" y="128">R/2</text>
            <text fill="black" x="210" y="278">-R/2</text>
            <text fill="black" x="210" y="353">-R</text>
            <text fill="black" x="387" y="190">X</text>
            <text fill="black" x="210" y="10">Y</text>
            <!--- Areas --->
            <polygon fill="pink" fill-opacity="0.4" points="200,200 200,50 350,50 350,200" stroke="black"></polygon>
            <polygon fill="yellow" fill-opacity="0.4" points="200,200 200,275 350,200" stroke="black"></polygon>
            <path d="M 125,200 A 75,75  90 0,1 200,125 L 200,200 Z" fill="orange" fill-opacity="0.4"
                  stroke="black"></path>
            <!--- Shot shadow --->
            <circle id="dot" fill="blue" color="blue" r="0" cx="0" cy="0"></circle>
            <!--- Previous shots --->
            <%
                Object rawHistory = application.getAttribute("history");
                LinkedList<Data> history;
                if (rawHistory != null) {
                    if (rawHistory instanceof LinkedList
                            && !((LinkedList<?>) rawHistory).isEmpty()
                            && ((LinkedList<?>) rawHistory).getFirst() instanceof Data) {
                        history = (LinkedList<Data>) rawHistory;
                    } else {
                        System.out.println("Attempt`s history can't be matched.");
                        history = new LinkedList<>();
                    }
                } else {
                    history = new LinkedList<>();
                }
                if (!history.isEmpty()) {
                    for (Data shot : history) {
                        double cx = 200 + 150 / shot.getR() * shot.getX();
                        double cy = 200 - 150 / shot.getR() * shot.getY();
                        String color;
                        if (shot.getResult().equals("TRUE")) {
                            color = "green";
                        } else color = "red";
                        out.println("<circle fill=\"" + (color) + "\" color=\"" +
                                color + "\" r=\"3\"" + "\" cx=\"" + cx + "\" cy=\"" + cy + "\"></circle>");
                    }
                }
            %>
        </svg>
        <div class="form">
            <form id="form" name="form" method="post">
                <div class="x">
                    <label for="x_value">X value</label>
                    <input type="text" maxlength="10" name="x_value" id="x_value">
                    <div class="error_text" id="error_x"></div>
                </div>
                <div class="y">
                    <label for="y_value1">Y value</label>
                    <input type="button" name="y_value" id="y_value1" value="-3">
                    <input type="button" name="y_value" id="y_value2" value="-2">
                    <input type="button" name="y_value" id="y_value3" value="-1">
                    <input type="button" name="y_value" id="y_value4" value="0">
                    <input type="button" name="y_value" id="y_value5" value="1">
                    <input type="button" name="y_value" id="y_value6" value="2">
                    <input type="button" name="y_value" id="y_value7" value="3">
                    <input type="button" name="y_value" id="y_value8" value="4">
                    <input type="button" name="y_value" id="y_value9" value="5">
                    <p id="y_value_text"></p>
                    <div class="error_text" id="error_y"></div>
                </div>
                <div class="r">
                    <label for="r_value">R value:</label>
                    <select id="r_value" size="1" name="r_value">
                        <option disabled selected>Select coordinate R</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    <div class="error_text" id="error_r"></div>
                </div>
                <div class="buttons">
                    <input id="send" type="submit" value="SUBMIT">
                    <input type="reset" value="RESET" onclick="clean_errors(), $('#dot').attr('r', '0px')">
                </div>
            </form>
        </div>
    </section>
    <section class="table_section">
        <table class="table">
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Current time</th>
                <th>Execution time</th>
                <th>Match</th>
            </tr>

            <%
                if (rawHistory instanceof LinkedList) {
                    history = (LinkedList) rawHistory;
                    for (Iterator it = history.descendingIterator(); it.hasNext(); ) {
                        Object attempt = it.next();
                        if (attempt instanceof Data) {
                            Data responseBean = (Data) attempt;
                            out.println("<tr><td>" + responseBean.getX()
                                    + "</td><td>" + responseBean.getY()
                                    + "</td><td>" + responseBean.getR()
                                    + "</td><td>" + responseBean.getCurrentTime()
                                    + "</td><td>" + responseBean.getDuration()
                                    + "</td><td>" + responseBean.getResult() + "</td></tr>");
                        }
                    }
                }
            %>
        </table>
    </section>
</main>
<script src="js/mouse_shot.js"></script>
<script>
    $(document).on('click', 'svg', function (event) {
        mouse_shot(event);
    });
</script>
<script src="js/validation.js"></script>
<script src="js/form.js"></script>
</body>
</html>