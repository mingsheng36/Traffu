'''
Created on 9 Mar 2015

@author: Jayden
'''
from django.db.models import Model, CharField

class News(Model):
    news = CharField(max_length=10000, unique=True)
    def __unicode__(self):
        return self.news

class Ids(Model):
    ids = CharField(max_length=100000, unique=True)
    state = CharField(max_length=20)
    exp_way = CharField(max_length=10)
    def __unicode__(self):
        return self.ids