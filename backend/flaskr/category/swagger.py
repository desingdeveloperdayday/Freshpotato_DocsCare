from flask_restplus import Namespace, fields

api = Namespace('categories', description='유저 카테고리 관련 api')

category = api.model('Category', {
    'id': fields.String(required=True, description='category id'),
    'name': fields.String(required=True, description='The category name'),
})

Categories = [
    {'id': 'felix', 'name': 'Felix'},
]