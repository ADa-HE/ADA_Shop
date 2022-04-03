<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>ADA-Shopper</title>
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
        Vector<Products> list = (Vector<Products>) request.getAttribute("vector");
        Vector<Categories> vectorCate = (Vector<Categories>) request.getAttribute("vectorCate");
        Integer pageIndex = (Integer) request.getAttribute("pageIndex");
        Integer totalRecord = (Integer) request.getAttribute("totalRecord");

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
                                <a href="ControllerProduct"><img src="images/home/logo.png" alt="" /></a>
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
                                    <c:if test="${sessionScope.Username.user==null}">
                                        <li>
                                            <a href="LoginControl"  ><i class="fa fa-user"></i> Register/Login</a>
                                        </li>
                                    </c:if>

                                    <c:if test="${sessionScope.Username.user!=null}">
                                        <li>
                                            <button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown" style="margin-top: 0px;margin-right: 0px">
                                                Tài khoản<br>${sessionScope.Username.user}
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a href="ControllerCustomer?do=pdateustomer">Tài Khoản của tôi</a></li>
                                                <li><a href="SessionCart?do=listOderDetail">Đơn hàng của tôi</a></li>
                                                <li><a href="LoginControl?do=logout">Logout</a></li>

                                            </ul>
                                        </li> 
                                    </c:if>
                                    <li><a href="" class="active"><i class="fa fa-star"></i> Wishlist</a></li>
                                    <li><a href="LoginControl" ><i class="fa fa-crosshairs"></i> Checkout</a></li>
                                    <li><a href="SessionCart?do=showcart"><i class="fa fa-shopping-cart"></i>Cart <span class="badge bg-dark text-white ms-1 rounded-pill"> ${sessionScope.sizeProCart==null?0:sessionScope.sizeProCart} </span>
                                        </a></li>
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
                                <input required   name="searchProName" class="search_box " type="text" placeholder="Search" /> 
                                <input type="submit" name="submit">
                            </form>


                        </div>
                    </div>
                </div>
            </div><!--/header-bottom-->
        </header><!--/header-->


        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2 style="margin: 0px">Category</h2>
                            <div class="panel-group category-products" id="accordian" ><!--category-productsr-->
                                <div class="panel panel-default">
                                    <div class="panel-heading " style="border-bottom-color: #CCCCC6;border-style: solid">
                                        <%for (Categories cate : vectorCate) {%>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <button class="btn btn-default" id="<%=cate.getCategoryID()%>"  > 
                                                        <a href="ControllerProduct?cateid=<%=cate.getCategoryID()%>"><%= cate.getCategoryName()%>⪼</a></h4> </button>


                                            </div>
                                        </div>

                                        <%}%>

                                    </div>
                                </div>
                            </div><!--/category-products-->

                            <div class="shipping text-center"><!--shipping-->
                                <img src="images/home/shipping.jpg" alt="" />
                            </div><!--/shipping-->
                        </div>
                    </div>

                    <div class="col-sm-9 padding-right">
                        <table>

                            <div class="features_items"><!--features_items-->
                                <h2 class="title text-center">Features Items</h2>

                                <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                                    <div class="carousel-inner">
                                        <% for (Products pro : list) {%>
                                        <div class="col-sm-4 container">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo ">
                                                        <img style="width: 249px;height: 268px;"  src="<%=pro.getImages()%>" alt="" />
                                                        <p style="font-family: sans-serif; font-size: 15px"><%=pro.getProductName()%></p>
                                                        <p><b>Brand:</b> Phố trong làng</p>
                                                        <div class="rating-area" >
                                                            <div class="col-sm-4" style="padding: 0px; text-align: left;">
                                                                <i class="fa fa-star color"></i>
                                                                <i class="fa fa-star color"></i>
                                                                <i class="fa fa-star color"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                            </div>
                                                            <div class="col-sm-6" style="padding: 0px; ">
                                                                | Còn lại: <%=pro.getUnitsInStock()%>
                                                            </div>
                                                        </div><!--/rating-area-->	
                                                        <div>
                                                            <div class="cartdetail" >
                                                                <%=pro.getUnitPrice()%>₫
                                                            </div>
                                                            <div>
                                                                <c:if test="<%= pro.getUnitsInStock() == 0%>"> 
                                                                    <button id="Product is out of stock " onclick="myFunction1(this.id)" type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart" ></i>
                                                                    </c:if>   
                                                                    <c:if test="<%= pro.getUnitsInStock() != 0%>"> 
                                                                        <button id="<%=pro.getProductName()%>" onclick="myFunction(this.id)" type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart" ></i>
                                                                        </c:if>  
                                                                        <c:if test="${requestScope.temppg!=null}"> 
                                                                            <a href="SessionCart?do=addtoCart&proid=<%=pro.getProductID()%>&page=<%= pageIndex%>&cateid=${requestScope.temppg}" 
                                                                               >Add to Cart</a></button>
                                                                        </c:if>
                                                                        <c:if test="${requestScope.temppg==null}"> 
                                                                        <a href="SessionCart?do=addtoCart&proid=<%=pro.getProductID()%>&page=<%= pageIndex%>"  
                                                                           >Add to Cart</a></button>
                                                                    </c:if>
                                                            </div>

                                                        </div>
                                                    </div>	
                                                </div>
                                            </div>
                                        </div>
                                        <%}%>
                                    </div>
                                </div>
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination">
                                        <li id="containerbot" class="page-item pagger"><a></a></li>
                                    </ul>
                                </nav>
                                <div  class="">  </div>
                            </div>
                    </div>
                </div>
            </div>


        </table>
    </div><!--features_items-->
</div>
</div>
</div>
</section>

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
                <p class="pull-right">Designed by <span><a target="_blank" href="">ADA</a></span></p>
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
                                                                        pagger("containerbot",<%=pageIndex%>,<%=totalRecord%>, 2,${requestScope.temppg==null?"0":requestScope.temppg},${requestScope.temppgsearch==null?"0":1},"${requestScope.temppgsearch}");
                                                                        function myFunction(x) {
                                                                            alert("Add " + x + " to Cart Successful");
                                                                        }
                                                                        function myFunction1(x) {
                                                                            alert(x + " Lần sau nhanh tay hơn bạn nhé");
                                                                        }
</script>
</body>
</html>