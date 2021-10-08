from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient
from datetime import datetime

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.dbStock


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/post', methods=['POST'])
def save_post():
    title = request.form['title']
    contents = request.form['contents']
    time = datetime.now().strftime('%Y.%m.%d %H:%M:%S')

    db_list = list(db.articles.find({}))
    idx = 1
    for number in db_list:
        if idx <= number['idx']:
            idx = number['idx']+1

    doc = {
        'idx': idx,
        'title': title,
        'contents': contents,
        'reg_date': time
    }
    db.articles.insert_one(doc)

    return jsonify({'msg': '포스팅 성공!'})


@app.route('/post', methods=['GET'])
def get_post():
    articles = list(db.articles.find({}, {'_id': False}))
    return jsonify({'all_articles': articles})


@app.route('/post', methods=['DELETE'])
def delete_post():
    id = request.form['idx']
    db.articles.delete_one({'idx': int(id)})

    return jsonify({'result': 'success','msg': '삭제 성공'})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)