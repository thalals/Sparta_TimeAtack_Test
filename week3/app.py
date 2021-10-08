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
        'reg_date': time,
        'view' : 0
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


@app.route('/articles', methods=['GET'])
def articles():
    id = request.args.get('idx')
    article = db.articles.find_one({'idx': int(id)}, {'_id': False})
    return jsonify({'articles': article})

@app.route('/articles', methods=['UPDATE'])
def articlesUpdate():
    id = request.args.get('idx')
    title = request.args.get('title')
    contents = request.args.get('contents')

    article = db.articles.update({'idx': int(id)}, {'title' :title, 'contents' : contents} )
    return jsonify({'articles': article})

@app.route('/view', methods=['GET'])
def viewUp():
    id = request.args.get('idx')
    view = db.articles.find_one({'idx': int(id)}, {'_id': False})['view']+1

    article = db.articles.update({'idx': int(id)}, {'view' :view} )
    return jsonify({'articles': article})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)