<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.bean.Via"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>


    <!-- Header Carousel -->
    <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide One');"></div>
                <div class="carousel-caption">
                    <h2>Caption 1</h2>
                </div>
            </div>
            <div class="item">
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Two');"></div>
                <div class="carousel-caption">
                    <h2>Caption 2</h2>
                </div>
            </div>
            <div class="item">
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Three');"></div>
                <div class="carousel-caption">
                    <h2>Caption 3</h2>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
    </header>

    <!-- Page Content -->
    <div class="container">
        
        <!-- Ultimas Vias -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Ultimas Sectores</h2>
            </div>
            <%
            	ArrayList<Sector> sectores = (ArrayList<Sector>)request.getAttribute("ultimos_sectores");            	
            	Sector s = null;
       			for(int i = 0 ; i < sectores.size() ; i++){
       				s = sectores.get(i);
       				String img_path = Constantes.IMG_DEFAULT_SECTOR;	      			
	      			if ( !img_path.equals( s.getImagen())){
	      				img_path = Constantes.IMG_WEP_PATH + s.getImagen();	      				
	      			}else{
	      				img_path = "img/" + img_path;
	      			}
            %>
	            <div class="col-md-4 col-sm-6">
	               <figure>
	                    <img class="img-responsive img-portfolio img-hover" src="<%=img_path%>" alt="">
	                	<figcaption class="text-center">
	                		<p><%=s.getNombre()+" (" + s.getZona().getNombre()+ ")"  %></p>
	<%-- 						<a href="<%=Constantes.CONTROLLER_VIA%>?id=<%=v.getId()%>" ><%=v.getNombre()%></a> --%>
						</figcaption>
					</figure>
	            </div>
            <%
	           	} //end for
	        %>
        </div>	<!-- END Ultimas Vias -->

        <!-- Features Section -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Modern Business Features</h2>
            </div>
            <div class="col-md-6">
                <p>The Modern Business template by Start Bootstrap includes:</p>
                <ul>
                    <li><strong>Bootstrap v3.2.0</strong>
                    </li>
                    <li>jQuery v1.11.0</li>
                    <li>Font Awesome v4.1.0</li>
                    <li>Working PHP contact form with validation</li>
                    <li>Unstyled page elements for easy customization</li>
                    <li>17 HTML pages</li>
                </ul>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis, omnis doloremque non cum id reprehenderit, quisquam totam aspernatur tempora minima unde aliquid ea culpa sunt. Reiciendis quia dolorum ducimus unde.</p>
            </div>
            <div class="col-md-6">
                <img class="img-responsive" src="http://placehold.it/700x450" alt="">
            </div>
        </div>
        <!-- /.row -->

        <hr>

        <!-- Call to Action Section -->
        <div class="well">
            <div class="row">
                <div class="col-md-8">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestias, expedita, saepe, vero rerum deleniti beatae veniam harum neque nemo praesentium cum alias asperiores commodi.</p>
                </div>
                <div class="col-md-4">
                    <a class="btn btn-lg btn-default btn-block" href="#">Call to Action</a>
                </div>
            </div>
        </div>

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

<jsp:include page="includes/foot.jsp"></jsp:include>
    