import{d as m,a3 as g,a2 as v,B as f,b as h,e as w,f as e,i,ai as u,u as t,aj as b,ak as B}from"./index.1ab5ed35.js";import{_ as C}from"./plugin-vue_export-helper.21dcd24c.js";import{E as d}from"./index2.9043b619.js";import"./index2.3dd85f37.js";import"./plugin-vue_export-helper.8ab27d33.js";const F=a=>(b("data-v-74c12cb8"),a=a(),B(),a),x={class:"loginContainer"},k={class:"form-wrapper"},y=F(()=>e("div",{class:"header"},"login",-1)),E={class:"input-wrapper"},I={class:"border-wrapper"},L={class:"border-wrapper"},S=m({name:"Login",setup(a){const p=g(),n=v();let s=f({uname:"",passwd:""});const c=async()=>{await p.login(s)?(n.push({path:"/home"}),d({message:"\u767B\u5F55\u6210\u529F"})):d({message:"\u767B\u5F55\u5931\u8D25\uFF0C\u8BF7\u68C0\u67E5\u60A8\u7684\u7528\u6237\u540D\u6216\u5BC6\u7801\u662F\u5426\u6B63\u786E"})},l=()=>{n.push({name:"register"})};return(_,o)=>(h(),w("div",x,[e("div",k,[y,e("div",E,[e("div",I,[i(e("input",{type:"text","onUpdate:modelValue":o[0]||(o[0]=r=>t(s).uname=r),name:"username",placeholder:"username",class:"border-item"},null,512),[[u,t(s).uname]])]),e("div",L,[i(e("input",{type:"password","onUpdate:modelValue":o[1]||(o[1]=r=>t(s).passwd=r),name:"password",placeholder:"password",class:"border-item"},null,512),[[u,t(s).passwd]])])]),e("div",{class:"action"},[e("div",{class:"btn",onClick:l},"register"),e("div",{class:"btn",onClick:c},"login")])])]))}});var j=C(S,[["__scopeId","data-v-74c12cb8"]]);export{j as default};
