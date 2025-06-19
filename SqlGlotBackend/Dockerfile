FROM python:3-alpine3.22

COPY . .

EXPOSE 8080

RUN python3 -m pip install --no-cache-dir -r requirements.txt

CMD ["gunicorn", "--bind", "0.0.0.0:8080", "main:app"]
