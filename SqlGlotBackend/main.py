from flask import Flask, request, jsonify
from sqlglot import transpile, Dialect
from flask_cors import CORS


app = Flask(__name__)
CORS(app)

@app.route("/supported", methods=["GET"])
def supported():
    return jsonify(sorted(Dialect.classes.keys()))

@app.route("/translate", methods=["POST"])
def translate():
    input_dialect = request.args.get("input", "").lower()
    output_dialect = request.args.get("output", "").lower()
    data = request.get_json()

    if not data or "query" not in data:
        return "Missing query", 400

    query = data["query"]

    try:
        result = transpile(query, read=input_dialect, write=output_dialect)
        return result[0]
    except Exception as e:
        return f"Translation error: {str(e)}", 400

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8080, debug=False)

