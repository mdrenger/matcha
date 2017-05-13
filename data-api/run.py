import json

from shapely.geometry import shape, Point

from flask import Flask, url_for
app = Flask(__name__)

shapeA=[]
shapeAB=[]
stations=[]
zones = set()

@app.route('/')
def api_root():
    return 'Welcome'


@app.route('/stations')
def api_stations():
    return json.dumps(stations)

@app.route('/shape/<shape>')
def api_shape(shape):
    a=[]
    if shape == "A":
     a= shapeA
    if shape == "AB":
     a= shapeAB
    return json.dumps(a)


@app.route('/test')
def api_test():
    ticket = {"name":"Einzelticket VBB",'price':500,'duration':"von jetzt bis in 2h",'persons':1,'modes':['S-Bahn','U-Bahn','Bus']}
    return json.dumps({'tickets':[ticket]})

@app.route('/getZones')
def api_getZones():
    return json.dumps(list(zones)) 

@app.route('/getStationsByZone/<z>')
def api_getStationsByZone(z):
    return json.dumps(list(filter(lambda x: x['zone'] == z, stations)))

@app.route('/getZoneForCoordinate/<coords>')
def api_getZoneForCoordinate(coords):
    lat, lon = coords.split(',')
    lat = float(lat)
    lon = float(lon)
    point = Point(lon, lat)
    polygonA = shape(shapeA)
    polygonAB = shape(shapeAB)

    if polygonA.contains(point):
        return 'A'
    elif polygonAB.contains(point):
        return 'B'
    else:
        return 'C'

if __name__ == '__main__':
    shapeA = json.load(open('data/shapeA.json'))
    shapeAB = json.load(open('data/shapeAB.json'))
    stations = json.load(open('data/stations.json'))
    
    for a in stations:
        zones.add(a['zone'])
    
    app.run(host='0.0.0.0')
