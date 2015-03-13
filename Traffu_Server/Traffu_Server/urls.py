from django.conf.urls import patterns, include, url
from django.contrib import admin
import views

urlpatterns = patterns('',
    url(r'^$', views.index),
    url(r'^refresh$', views.refresh),
    url(r'^update$', views.update),
    url(r'^admin/', include(admin.site.urls)),
)
