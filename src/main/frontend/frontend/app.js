const send = require('koa-send');
const serve = require('koa-static');
const path = require('path');
const Koa = require('koa');

const staticDir = path.resolve(__dirname, 'public');
const app = new Koa();

app.use(serve(staticDir));
app.use(async function(ctx) {
  await send(ctx, 'index.html', { root: staticDir });
});

app.listen(3000, () => {
  console.log('Server listen on localhost:3000');
});




