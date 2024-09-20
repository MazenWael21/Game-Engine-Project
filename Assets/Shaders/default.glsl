#type vertex
#type 330 core

layout (location=0) in vec3 apos;

layout (location=1) in vec4 acolor;

out vec4 fcolor ;

void main() {
fcolor = acolor;
gl_position = vec4(apos,1.0);
}

#type fragment
#version 330 core 

void main(){

    color = fcolor;
}




