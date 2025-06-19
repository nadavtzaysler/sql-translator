from pydantic import BaseModel

class TranslationRequest(BaseModel):
    query: str
