from fastapi import FastAPI

app = FastAPI()

@app.get("/{path}")
def trasformar(path: str):

    return {"Hello": path}
