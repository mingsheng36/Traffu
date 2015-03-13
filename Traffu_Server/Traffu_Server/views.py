'''
Created on 9 Mar 2015

@author: Tan Ming Sheng
'''
from django.http import HttpResponse
from lxml import html
from models import News, Ids
import requests, json, urllib2, re

exp_way = ['ECP', 'AYE', 'PIE', 'TPE', 'SLE', 'KJE', 'CTE', 'MCE', 'KPE', 'BKE']

def index(request):
    if request.method == 'GET':
        return HttpResponse("Welcome to traffu!")

'''
To get update registration id to exit
''' 
def update(request):
    if request.method == 'GET':
        try:
            ids = request.GET['rid']
            state = request.GET['state']
            exp_way = request.GET['expway']
            print state + " " + exp_way
            if Ids.objects.filter(ids=ids).count() == 0:
                Ids(ids=ids, state=state, exp_way=exp_way).save()
            else:
                Ids.objects.update(ids=ids, state=state, exp_way=exp_way)
            return HttpResponse("success")
        except Exception as err:
            print err  
        return HttpResponse("fail")  
    
'''
To get new updates
''' 
def refresh(request):
    if request.method == 'GET':
        try:
            url =  "http://www.onemotoring.com.sg/publish/onemotoring/en/on_the_roads/traffic_news.main.step1.html?viewType=RoadName&filterBy=Expressways&sortBy=8"
            req = urllib2.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
            res = urllib2.urlopen(req)
            raw_news = html.fromstring(res.read()).xpath("/html/body/div[3]/div[3]/div[5]/div/div[3]/div[1]/div[1]/form/div/div[3]/div/div[1]/div/div[3]/p/text()")
            
            news = []
            for n in raw_news:
                news.append(re.sub(r'[^\x00-\x7F]+',' ', n))
            
            # remove non existing old news
            old_news = News.objects.all()
            for o in old_news:
                if o.news not in news:
                    News.objects.get(news=o).delete()
                    print "deleted " + o.news

            # check for new updates
            updated_news = []  # for sending response purposes
            for n in news:
                # have new updates
                if n not in old_news.values_list('news', flat=True):
                    News(news=n).save()
                    print "saved " + n
                    updated_news.append(n)
                    
            '''
            DEVELOPMENT PURPOSE
            '''
            updated_news.append("12/03 17:42 Heavy Traffic on PIE (towards Changi Airport) between Adam Rd and Paya Lebar Rd.")
            updated_news.append("12/03 18:48 Heavy Traffic on CTE (towards SLE) at Moulmein Rd Exit.")
            updated_news.append("12/03 17:21 Heavy Traffic on PIE (towards Changi Airport) at Lornie Rd Exit.")
            
            # update apps on new addition
            for e in exp_way:
                new_update_news = []
                formatted_rids = [] 
                for n in updated_news:
                    if "on " + e in n:
                        
                        print e + " - " + n
                        new_update_news.append(n)
                
                if len(new_update_news) > 0:
                    rids = Ids.objects.filter(exp_way=e.lower(), state='online')
                    if rids.count() > 0:
                        for rid in rids:
                            formatted_rids.append(str(rid))
                        last_news = new_update_news.pop()
                        payload = {}
                        payload['news'] = e.lower() + "," + last_news
                        send_gcm(formatted_rids, payload)

            if len(updated_news) > 0:
                updated_news = '<br/>'.join(updated_news)
            else:
                updated_news = "No Updates"
            
            return HttpResponse("<h1>Traffic News Updates</h1>" + updated_news)
                    
        except Exception as err:
            print err
            
def send_gcm(rids, payload):
    
    print "Sending data to:" + str(rids)
    print payload
    
    google_url = 'https://android.googleapis.com/gcm/send'
    headers = {'content-type': 'application/json', 'Authorization':'key=AIzaSyCVvLAiWbVTK8yxQjbt73xdkj0vOZ2hlhQ'}
    data = {'registration_ids': rids, 'data': payload}                 
    response_from_google = requests.post(google_url, data=json.dumps(data), headers=headers)
    
    print response_from_google.text