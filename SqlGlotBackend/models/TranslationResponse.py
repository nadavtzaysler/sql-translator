from pydantic import BaseModel

class TranslationResponse(BaseModel):
    translated: str
