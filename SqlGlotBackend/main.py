import os
from fastapi import FastAPI, Query, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import PlainTextResponse
from sqlglot import transpile, Dialect
from typing import List

import uvicorn

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
    input: str = Query(..., description="Source dialect"),
    output: str = Query(..., description="Target dialect"),
    body: TranslationRequest = ...
):
    input_dialect = input.lower()
    output_dialect = output.lower()

    if input_dialect not in SUPPORTED_DIALECTS or output_dialect not in SUPPORTED_DIALECTS:
        raise HTTPException(
            status_code=400,
            detail="Unsupported dialect."
        )

    try:
        result = transpile(body.query, read=input_dialect, write=output_dialect)
        return result[0]
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Translation error: {str(e)}")

if __name__ == "__main__":
    port = int(os.environ.get("PORT", 8080))
    uvicorn.run("main:app", host="0.0.0.0", port=port)
