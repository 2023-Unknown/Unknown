FROM nginx:1.22.0

RUN rm /etc/nginx/conf.d/default.conf

COPY conf.d/default.conf /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/nginx.conf

CMD ["nginx", "-g", "daemon off;"]

# 포트는 80
EXPOSE 80
