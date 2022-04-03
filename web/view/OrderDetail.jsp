<%-- 
    Document   : Cart
    Created on : Mar 3, 2022, 5:14:23 PM
    Author     : ADA
--%>
<%@page import="java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Products"%>
<%@page import="entity.Status"%>
<%@page import="entity.OrderDetails"%>
<%@page import="entity.ItemProductCart"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cart </title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    </head><!--/head-->
    <%
        ResultSet rs1 = (ResultSet) request.getAttribute("rs1");
        Vector<Status> vectorsta = (Vector<Status>) request.getAttribute("vectorsta");
        String temp = "";
        int temp1 = 0;
    %>
    <body>
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    HE151385-Phạm Văn Đạt
                </div>
            </div><!--/header_top-->

            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="logo pull-left">
                                <a href="index.html"><img src="images/home/logo.png" alt="" /></a>
                            </div>
                            <div class="btn-group pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
                                        VIETNAM
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="">Canada</a></li>
                                        <li><a href="">UK</a></li>
                                    </ul>
                                </div>

                                <div class="btn-group">
                                    <button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
                                        VND
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="">Canadian Dollar</a></li>
                                        <li><a href="">Pound</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="shop-menu pull-right">
                                <ul class="nav navbar-nav">
                                    <li><a href=""><i class="fa fa-user"></i> ${sessionScope.Username.user==null?"Wellcome":sessionScope.Username.user} </a></li>
                                    <li><a href="" ><i class="fa fa-star"></i> Wishlist</a></li>
                                    <li><a href="LoginControl" ><i class="fa fa-crosshairs"></i> Checkout</a></li>
                                    <li><a href="SessionCart?do=showcart" class="active"><i class="fa fa-shopping-cart"></i>Cart <span class="badge bg-dark text-white ms-1 rounded-pill"> ${sessionScope.sizeProCart==null?0:sessionScope.sizeProCart} </span>
                                        </a></li>

                                    <li><a href="LoginControl">${sessionScope.Username.user==null?"Login":""}<a href="LoginControl?do=logout">${sessionScope.Username.user!=null?"Logout":""}</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->

            <div class="header-bottom"><!--header-bottom-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-9">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                            <div class="mainmenu pull-left">

                            </div>
                        </div>
                        <div class="col-sm-3">

                            <form action="ControllerProduct">
                                <input name="searchProName" class="search_box " type="text" placeholder="Search"/> 
                                <input type="submit" name="submit" value="Search">
                            </form>


                        </div>
                    </div>
                </div>
            </div><!--/header-bottom-->
        </header><!--/header-->

        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="ControllerProduct">Home</a></li>
                        <li class="active">Các đơn hàng của bạn</li>
                    </ol>
                </div>
                <div class="table-responsive cart_info ">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="price" style="text-align: center;">ID</td>
                                <td class="image" style="text-align: center;">Item Name</td>

                                <td class="price" style="text-align: center;">Price</td>
                                <td class="quantity" style="text-align: center;">Quantity</td>
                                <td class="total" style="text-align: center;">Total</td>
                                <td class="description">Status</td>
                            </tr>
                        </thead>
                        <tbody>
                            <% while (rs1.next()) {%>
                            <tr>
                                <td class="text-center">
                                    <%=rs1.getInt(1)%>
                                </td>
                                <td class="cart_description">
                                    <div class="row">
                                        <h4 class="col-md-6" style="text-align: center;padding-top: 30px"><a href="" ><%=rs1.getString(2)%></a> </h4>
                                        <a href="" class="col-md-6"><img style="width: 110px;height: 110px;" src="<%=rs1.getString(3)%>" alt=""/></a>
                                    </div>

                                </td>
                                <td class="cart_description">
                                    <%=rs1.getDouble(4)%>
                                </td>
                                <td class="cart_price" style="text-align: center;">
                                    <p> <%=rs1.getInt(5)%> </p>
                                </td>
                                <td class="cart_quantity" style="text-align: center;">
                                   <%=rs1.getString(6)%>
                                </td>
                                <%temp1 = rs1.getInt(7); %>
                                    
                               
                                <td class="cart_total ">
                                   <%for (Status e : vectorsta) { %>
                                   <p><%=e.getId()==temp1?e.getName():"" %></p>
                                    <%}%>
                                </td>
                            </tr>  

                            <%}%>

                        </tbody>
                    </table>
                </div>
            </div>
        </section> <!--/#cart_items-->



        <footer id="footer"><!--Footer-->
            <div class="footer-top">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="companyinfo">
                                <h2><span>ADA</span>-shopper</h2>
                                <p>nothing is importsible</p>
                            </div>
                        </div>
                        <div class="col-sm-7">
                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="images/home/iframe1.jpg" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="images/home/iframe2.jpg" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="images/home/iframe3.jpg" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="images/home/iframe4.jpg" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="address">
                                <img src="images/home/map.png" alt="" />
                                <p>Ha Noi - Viet Nam 36 phố phường</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-bottom">
                <div class="container">
                    <div class="row">
                        <p class="pull-left">Copyright © 2013 E-SHOPPER Inc. All rights reserved.</p>
                        <p class="pull-right">Designed by <span><a target="_blank" href="">Themeum</a></span></p>
                    </div>
                </div>
            </div>
        </footer><!--/Footer-->



        <script src="../js/jquery.js"></script>
        <script src="../js/price-range.js"></script>
        <script src="../js/jquery.scrollUp.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery.prettyPhoto.js"></script>
        <script src="../js/main.js"></script>
    </body>
</html>