import json

from flask import Flask, url_for
app = Flask(__name__)


@app.route('/')
def api_root():
    return 'Welcome'


@app.route('/stations')
def api_stations():
    return json.dumps(json.load(open('data/stations.json')))

@app.route('/shape/<shape>')
def api_shape(shape):
    a=[]
    if shape == "A":
     a= json.load(open('data/shapeA.json'))
    if shape == "AB":
     a= json.load(open('data/shapeAB.json'))
    return json.dumps(a)


if __name__ == '__main__':
    app.run(host='0.0.0.0')
