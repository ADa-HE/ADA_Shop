<%@page import="entity.Customers"%>
<%@page import="entity.ItemProductCart"%>
<%@page import="entity.Categories"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Products"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Checkout | E-Shopper</title>
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
        Vector<ItemProductCart> list = (Vector<ItemProductCart>) request.getAttribute("listProCart");
        double temp = 0;
        String notice = (String) request.getAttribute("notice");
        Customers custom = (Customers)request.getAttribute("cust");
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
                                    <li><a href=""><i class="fa fa-user"></i> ${sessionScope.Username.user==null?"":sessionScope.Username.user}</a></li>
                                    <li><a href="" class="active"><i class="fa fa-star"></i> Wishlist</a></li>
                                    <li><a href="checkout.html" ><i class="fa fa-crosshairs"></i> Checkout</a></li>
                                    <li><a href="SessionCart?do=showcart"><i class="fa fa-shopping-cart"></i>Cart <span class="badge bg-dark text-white ms-1 rounded-pill"> ${sessionScope.sizeProCart==null?0:sessionScope.sizeProCart} </span>
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
                        <li class="active">Check out</li>
                    </ol>

                    <div class="shopper-informations">
                        <div class="row">
                            <div class="col-sm-5 clearfix">
                                <div class="bill-to">
                                    <b>Bill To</b>
                                    <form action="SessionCart" method="post">

                                        <div class="form-one">

                                            <input name="PostalCode" type="text" placeholder="Postal Code*" value="<%=custom.getPostalCode() %>">
                                            <input name="ShipName" type="text" placeholder="Ship Name *" value="<%=custom.getPostalCode() %>">
                                            <input  name="ShipCountry" type="text" placeholder="Ship Country* " value="<%=custom.getPostalCode() %>">
                                            <input hidden name="do" type="text" value="checkout">
                                        </div>
                                        <div class="form-one">

                                            <input name="Shipaddress" type="text" placeholder="Ship Address*"  value="<%=custom.getPostalCode() %>">
                                            <input name="Shipcity" type="text" placeholder="Ship City* "  value="<%=custom.getPostalCode() %>">
                                        </div>
                                        <%if (notice == null) {// khác null là không có mặt hàng %>

                                        <input type="submit" value="submit">
                                        <%}%>
                                    </form>
                                </div>
                            </div>

                            <div class="col-sm-7 clearfix">	
                                <table class="table table-condensed ta">


                                    <thead>
                                        <tr class="cart_menu">
                                            <td style="text-align: center; "> <b>Item </b> </td>
                                            <td class="price"><b>Price</b></td>
                                            <td class="quantity"><b>Quantity</b></td>
                                            <td class="total"><b>Total</b></td>
                                            <td></td>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <%for (ItemProductCart item : list) {%>
                                        <tr>

                                            <td class="">
                                                <p class="col-md-6" style="text-align: center;padding-top: 30px"><a href="" ><%=item.getProduct().getProductName()%></a></p>
                                                <a href="" class="col-md-6"><img style="width: 110px;height: 110px;" src="<%=item.getProduct().getImages()%>" alt=""/></a>

                                            </td>
                                            <td class="price" style="text-align: center;padding-top: 30px">
                                                <p><%=item.getProduct().getUnitPrice()%></p>
                                            </td>

                                            <td class="quantity" style="text-align: center;padding-top: 30px">
                                                <div >
                                                    <input style="text-align: center" type="text" value="<%=item.getQuantity()%>" readonly size="1">
                                                </div>
                                            </td>
                                            <td class="total"style="text-align: center;padding-top: 30px"><%temp += item.getProduct().getUnitPrice() * item.getQuantity();%>
                                                <p class=""><%=item.getProduct().getUnitPrice() * item.getQuantity()%> </p>
                                            </td>

                                        </tr>
                                        <%}%>
                                        <%if (notice != null) {// khác null là không có mặt hàng %>
                                        <td>
                                                <%=notice %>
                                                
                                        </td    >

                                            <%}%>
                                            
                                            <td colspan="4">&nbsp;</td>
                                            <td colspan="2">
                                                <table class="table table-condensed total-result">
                                                    <tr>
                                                        <td>Cart Sub Total</td>
                                                        <td><%= temp%></td>
                                                    </tr>

                                                    <tr class="shipping-cost">
                                                        <td>Shipping Cost</td>
                                                        <td>Free</td>										
                                                    </tr>
                                                    <tr>
                                                        <td>Total</td>
                                                        <td><span><%=temp%></span></td>
                                                    </tr>
                                                    
                                                </table>
                                                    
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>			
                        </div>
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
                                            <img src="../images/home/iframe2.jpg" alt=""/>
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
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
        <script src="js/term.js"></script>
        <script>

            function myFunction(x) {
                alert("Add " + x + " to Cart Successful");
            }
        </script>
    </body>
</html>