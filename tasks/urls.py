from django.urls import path

from . import views

app_name = "tasks"

urlpatterns = [
    path("", views.task_list, name="list"),
    path("tasks/new/", views.task_create, name="create"),
    path("tasks/<int:task_id>/", views.task_detail, name="detail"),
    path("tasks/<int:task_id>/edit/", views.task_update, name="update"),
    path("tasks/<int:task_id>/subtasks/new/", views.subtask_create, name="subtask_create"),
    path(
        "tasks/<int:task_id>/subtasks/<int:subtask_id>/toggle/",
        views.subtask_toggle,
        name="subtask_toggle",
    ),
]
