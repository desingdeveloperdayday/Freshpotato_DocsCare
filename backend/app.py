import logging

from flask import Flask

import backend.db as db
import backend.flaskr as route
import backend.settings as settings

app = Flask(__name__)
app.config['MONGO_DBNAME'] = settings.MONGO_DB
app.config["MONGO_URI"] = 'mongodb://{}:{}@{}:{}/{}?authSource=admin&ssl=true'.format(
    settings.MONGO_USER,
    settings.MONGO_PASS,
    settings.MONGO_HOST,
    settings.MONGO_PORT,
    settings.MONGO_DB)

mongo = db.init_app(app)


def configure_app(app: Flask) -> None:
    app.config['SWAGGER_UI_DOC_EXPANSION'] = settings.SWAGGER_UI_DOC_EXPANSION
    app.config['RESTPLUS_VALIDATE'] = settings.RESTPLUS_VALIDATE


def initialize_app(app: Flask) -> None:
    configure_app(app)
    route.api.init_app(app)


@app.errorhandler(404)
def not_found(e):
    return '', 404


if __name__ != "__main__":
    gunicorn_logger = logging.getLogger("gunicorn.error")
    app.logger.handlers = gunicorn_logger.handlers
    app.logger.setLevel(gunicorn_logger.level)

if __name__ == "__main__":
    initialize_app(app)
    app.run(host="0.0.0.0", debug=True, port=5005)