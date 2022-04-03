<%-- 
    Document   : Cart
    Created on : Mar 3, 2022, 5:14:23 PM
    Author     : ADA
--%>

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
        Vector<ItemProductCart> listProCart = (Vector<ItemProductCart>) request.getAttribute("listProCart");
        double temp = 0;
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
                        <li class="active">Shopping Cart</li>
                    </ol>
                </div>
                <div class="table-responsive cart_info ">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="price" style="text-align: center;">ID</td>
                                <td class="image" style="text-align: center;">Item Name</td>
                                <td class="description"></td>
                                <td class="price" style="text-align: center;">Price</td>
                                <td class="quantity" style="text-align: center;">Quantity</td>
                                <td class="total" style="text-align: center;">Total</td>
                                <td class="total" style="text-align: center;">Remove</td>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (ItemProductCart item : listProCart) {%>

                            <tr>
                                <td class="text-center">
                                    <p><%=item.getProduct().getProductID()%></p>
                                </td>


                                <td class="cart_description">
                                    <div class="row">
                                        <h4 class="col-md-6" style="text-align: center;padding-top: 30px"><a href="" ><%=item.getProduct().getProductName()%></a></h4>
                                        <a href="" class="col-md-6"><img style="width: 110px;height: 110px;" src="<%=item.getProduct().getImages()%>" alt=""/></a>
                                    </div>

                                </td>
                                <td class="cart_description">

                                </td>
                                <td class="cart_price" style="text-align: center;">
                                    <p><%=item.getProduct().getUnitPrice()%></p>
                                </td>
                                <td class="cart_quantity" style="text-align: center;">
                                    <div class="cart_quantity_button "  >
                                        <div>

                                            <button>
                                                <a class="cart_quantity_up" href="SessionCart?do=plusItem&method=pluss&id=<%=item.getProduct().getProductID()%>" > + </a>
                                            </button>
                                            <input class="cart_quantity_input" type="text" name="quantity" value="<%=item.getQuantity()%>" autocomplete="off" size="1" 	>

                                            <button>
                                                <a class="cart_quantity_down" href="SessionCart?do=plusItem&method=minus&id=<%=item.getProduct().getProductID()%>" > - </a>
                                            </button>
                                        </div>

                                    </div>

                                </td><%temp
                                            += item.getProduct().getUnitPrice() * item.getQuantity();%>
                                <td class="cart_total ">
                                    <p class="" style="text-align: center;font-size: 16px"><%=item.getProduct().getUnitPrice() * item.getQuantity()%></p>
                                </td>
                                <td class="cart_delete" class="btn btn-default">
                                    <a class="cart_quantity_delete" href="SessionCart?do=removeItem&id=<%=item.getProduct().getProductID()%>"><i class="fa fa-times"></i></a>
                                </td>
                            </tr>  

                            <% }%>
                            <tr>
                                <td class="">


                                </td>


                                <td class="cart_description">
                                    <div class="row">
                                    </div>

                                </td>
                                <td class="cart_description">

                                </td>
                                <td class="cart_price" style="text-align: center;">
                                </td>
                                <td class="cart_quantity" style="text-align: center;">
                                    <div class="cart_quantity_button" >
                                        <p style="font-family: fantasy;font-size: 20px;padding-top: 10px " class="text-primary"> Grand total:</p>
                                    </div>

                                </td>
                                <td class="cart_total_price text-center" style="font-family: fantasy;font-size: 20px">
                                    <%=temp%>
                                </td>
                                <td class="cart_delete" >

                                    <a class="cart_info" href="SessionCart?do=removeItem">  Remove all</a>

                                </td>
                            </tr>
                            <tr>
                                <td class="text-center">

                                </td>


                                <td class="cart_description">
                                    <div class="row">
                                    </div>

                                </td>
                                <td class="cart_description">

                                </td>
                                <td class="cart_price" style="text-align: center;">
                                </td>
                                <td class="cart_quantity" style="text-align: center;">
                                    <div class="cart_quantity_button" >
                                    </div>

                                </td>
                                <td class="cart_total_price text-center" >
                                </td>
                                <td class="text-center" >
                                    <button type="button" class="btn btn-default ">
                                        <a class="cart_info" href="LoginControl">  Check out</a>
                                    </button>
                                </td>
                            </tr>  
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