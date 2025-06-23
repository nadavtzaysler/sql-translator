from fastapi import FastAPI, Query, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import PlainTextResponse
from sqlglot import transpile, Dialect
from typing import List

from models.ErrorResponse import ErrorResponse
from models.TranslationRequset import TranslationRequest

app = FastAPI(title="SQL Translator")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

SUPPORTED_DIALECTS = sorted(Dialect.classes.keys())

@app.get("/supported", response_model=List[str])
def get_supported_dialects():
    return SUPPORTED_DIALECTS

@app.post(
    "/translate",
    response_class=PlainTextResponse,
    responses={400: {"model": ErrorResponse}}
)
def translate(
    inputDialect: str = Query(..., description="Source dialect"),
    outputDialect: str = Query(..., description="Target dialect"),
    body: TranslationRequest = ...
):
    input_dialect = inputDialect.lower()
    output_dialect = outputDialect.lower()

    if input_dialect not in SUPPORTED_DIALECTS or output_dialect not in SUPPORTED_DIALECTS:
        raise HTTPException(status_code=400, detail="Unsupported dialect.")

    try:
        result = transpile(body.query, read=input_dialect, write=output_dialect)
        return result[0]
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Translation error: {str(e)}")
