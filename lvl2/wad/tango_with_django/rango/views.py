from django.http import HttpResponse

# Create your views here.
def index(request):
    return HttpResponse("Rango says hey there partner! </br> <a href='about'>About</a>")

def about(request):
    return HttpResponse("Rango says here is the about page")
