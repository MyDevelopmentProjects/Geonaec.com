<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section id="site-event-speakers" style="padding-top: 60px;padding-bottom: 60px;" class="site-event-speakers section-padding">
    <div class="container speakers-profile-carousel">
        <div class="event-speaker-profile swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="speaker-profile-details" style="opacity: 1 !important;align-items: unset !important;">
                        <div class="profile-image" data-animate="fadeInUp" data-delay="0.2s" data-duration="0.5s">
                            <img width="480" height="460"
                            style="background: url(/uploads/${object.image});
                                    height: 460px;
                                    background-size: cover;
                                    background-position: center center;">
                        </div>
                        <!-- /.profile-image -->

                        <div class="profile-content">
                            <div class="name-job" data-animate="fadeInUp" data-delay="0.4s" data-duration="0.5s">
                                <h3 class="name">
                                    ${object.title}
                                </h3>
                                <div class="job">
                                    <b>Created:</b> <fmt:formatDate value="${object.dateCreated}" pattern="MM-dd-yyyy"/>
                                </div>
                            </div>
                            <!-- /.name-job -->

                            <div class="biography" data-animate="fadeInUp" data-delay="0.6s" data-duration="0.5s">
                                ${object.description}
                                    <c:if test="${fn:length(object.array) > 0}">
                                        <p style="margin-top: 10px; border-top: solid 1px #e5e5e5;">
                                            <span>Attached Files</span><br />
                                            <c:forEach items="${object.array}" var="element" varStatus="loop">
                                                <a href="/uploads/${element}"
                                                   target="_blank">${element}</a><br/>
                                            </c:forEach>
                                        </p>
                                    </c:if>
                            </div>
                            <!-- /.biography -->
                        </div>

                    </div>
                    <c:if test="${not empty object.videoUrl}">
                        <iframe width="100%" height="460" src="https://www.youtube.com/embed/${object.videoUrl}"
                                frameborder="0"
                                gesture="media"
                                allow="encrypted-media"
                                allowfullscreen></iframe>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
