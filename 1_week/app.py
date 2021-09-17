from flask import Flask, render_template, jsonify, request

app = Flask(__name__)
from pymongo import MongoClient
client = MongoClient('localhost', 27017)
db = client.dbStock

## HTML 화면 보여주기
@app.route('/')
def homework():
    return render_template('index.html')


# 주문 목록보기(Read) API
@app.route('/show', methods=['GET'])
def view():
    market = request.args.get('market_give')
    sector = request.args.get('sector_give')
    tag = request.args.get('tag_give')
    done = list(db.stocks.find({"market":market, "sector":sector, "tag" : tag}, {'_id': False}))

    print(done)
    return jsonify({'done': done})


if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)