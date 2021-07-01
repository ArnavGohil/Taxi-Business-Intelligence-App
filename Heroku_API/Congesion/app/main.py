from flask import Flask, request, jsonify
import joblib
import traceback
import pandas as pd
import numpy as np

app = Flask(__name__) 

@app.route('/', methods=['POST'])
def main():
    rf = joblib.load("cong.pkl")
    if rf:
        try:
            json_ = request.json
            print(json_)
            query = pd.get_dummies(pd.DataFrame(json_))

            prediction = list(rf.predict(query))

            return jsonify({'prediction': str(prediction)})

        except:

            return jsonify({'trace': traceback.format_exc()})
    else:

        return ('No model here to use')

if __name__ == '__main__':
    app.run(port = process.env.PORT , debug=True)