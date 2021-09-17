from flask import Flask, render_template, jsonify, request
from bs4 import BeautifulSoup

app = Flask(__name__)
from pymongo import MongoClient
client = MongoClient('localhost', 27017)
db = client.dbStock

## HTML 화면 보여주기
@app.route('/')
def homework():
    return render_template('index.html')

#코스피 코스닥
@app.route('/start', methods=['GET'])
def start():
    lists = list(db.codes.find({'group':'market'},{'_id': False}))
    return jsonify({'lists' : lists})

# sector list
@app.route('/market', methods=['GET'])
def market_select():

    lists = list(db.codes.find({'group':'sector'}, {'_id': False}))

    return jsonify({'lists' : lists})

# tag_list
@app.route('/sector', methods=['GET'])
def sector_select():
    lists = list(db.codes.find({'group': 'tag'}, {'_id': False}))

    return jsonify({'lists': lists})

#  주식 목록 보내주기
@app.route('/tag', methods=['POST'])
def tag_select():
    market_name = request.form['market']
    sector_name = request.form['sector']
    tag_name = request.form['tag']

    market = db.codes.find_one({'group': 'market', 'name':market_name})['code']
    sector = db.codes.find_one({'group': 'sector', 'name':sector_name})['code']
    tag = db.codes.find_one({'group': 'tag', 'name':tag_name})['code']
    print(market,sector,tag)
    result_list = list(db.stocks.find({'market':market, 'sector':sector, 'tag':tag}, {'_id': False}))
    print(result_list)
    return jsonify({'result_lists': result_list})

@app.route('/stock', methods = ['POST'])
def save_info():
    info = request.form['name']
    code = db.stocks.find_one({'name': info})

    print(code)
    return jsonify(code)

if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)