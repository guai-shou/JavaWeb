var Se=Object.defineProperty,Ee=Object.defineProperties;var Pe=Object.getOwnPropertyDescriptors;var oe=Object.getOwnPropertySymbols;var Re=Object.prototype.hasOwnProperty,Fe=Object.prototype.propertyIsEnumerable;var re=(e,a,s)=>a in e?Se(e,a,{enumerable:!0,configurable:!0,writable:!0,value:s}):e[a]=s,B=(e,a)=>{for(var s in a||(a={}))Re.call(a,s)&&re(e,s,a[s]);if(oe)for(var s of oe(a))Fe.call(a,s)&&re(e,s,a[s]);return e},z=(e,a)=>Ee(e,Pe(a));import{d as O,b as f,e as k,f as L,c as S,K as Te,j as m,t as H,q as P,y as G,a9 as le,aa as ie,m as b,g as U,h as T,N as Le,O as E,r as se,x as Ue,u as o,ao as ge,l as D,k as M,F as _e,ap as De,V as Be,A as ze,s as Z,w as ue,X as Oe,a as Ne,p as He,I as je,ab as de,S as ce}from"./index.1ab5ed35.js";import{_ as ae,b as A,d as h,E as j,F as x,B as pe,v as ee,G as fe}from"./index2.3dd85f37.js";import{u as V,_ as X,w as be,l as Ie}from"./plugin-vue_export-helper.8ab27d33.js";import{t as ne,m as J,p as Me,n as Ve}from"./el-popper.4015840f.js";import{i as Ae}from"./el-input.340f03b2.js";import{u as qe}from"./index2.69e68361.js";import{z as We}from"./zoom-in.13d40803.js";const Ke=O({name:"Check"}),Xe={viewBox:"0 0 1024 1024",xmlns:"http://www.w3.org/2000/svg"},Ge=L("path",{fill:"currentColor",d:"M406.656 706.944 195.84 496.256a32 32 0 1 0-45.248 45.248l256 256 512-512a32 32 0 0 0-45.248-45.248L406.592 706.944z"},null,-1),Je=[Ge];function Qe(e,a,s,c,i,u){return f(),k("svg",Xe,Je)}var te=ae(Ke,[["render",Qe]]);const Ye=O({name:"Delete"}),Ze={viewBox:"0 0 1024 1024",xmlns:"http://www.w3.org/2000/svg"},xe=L("path",{fill:"currentColor",d:"M160 256H96a32 32 0 0 1 0-64h256V95.936a32 32 0 0 1 32-32h256a32 32 0 0 1 32 32V192h256a32 32 0 1 1 0 64h-64v672a32 32 0 0 1-32 32H192a32 32 0 0 1-32-32V256zm448-64v-64H416v64h192zM224 896h576V256H224v640zm192-128a32 32 0 0 1-32-32V416a32 32 0 0 1 64 0v320a32 32 0 0 1-32 32zm192 0a32 32 0 0 1-32-32V416a32 32 0 0 1 64 0v320a32 32 0 0 1-32 32z"},null,-1),et=[xe];function tt(e,a,s,c,i,u){return f(),k("svg",Ze,et)}var st=ae(Ye,[["render",tt]]);const at=O({name:"Document"}),nt={viewBox:"0 0 1024 1024",xmlns:"http://www.w3.org/2000/svg"},ot=L("path",{fill:"currentColor",d:"M832 384H576V128H192v768h640V384zm-26.496-64L640 154.496V320h165.504zM160 64h480l256 256v608a32 32 0 0 1-32 32H160a32 32 0 0 1-32-32V96a32 32 0 0 1 32-32zm160 448h384v64H320v-64zm0-192h160v64H320v-64zm0 384h384v64H320v-64z"},null,-1),rt=[ot];function lt(e,a,s,c,i,u){return f(),k("svg",nt,rt)}var it=ae(at,[["render",lt]]);const ke=Symbol("uploadContextKey"),ut=A({type:{type:String,default:"line",values:["line","circle","dashboard"]},percentage:{type:Number,default:0,validator:e=>e>=0&&e<=100},status:{type:String,default:"",values:["","success","exception","warning"]},indeterminate:{type:Boolean,default:!1},duration:{type:Number,default:3},strokeWidth:{type:Number,default:6},strokeLinecap:{type:h(String),default:"round"},textInside:{type:Boolean,default:!1},width:{type:Number,default:126},showText:{type:Boolean,default:!0},color:{type:h([String,Array,Function]),default:""},format:{type:h(Function),default:e=>`${e}%`}}),dt=O({name:"ElProgress",components:{ElIcon:j,CircleCheck:x,CircleClose:pe,Check:te,Close:ee,WarningFilled:fe},props:ut,setup(e){const a=V("progress"),s=S(()=>({width:`${e.percentage}%`,animationDuration:`${e.duration}s`,backgroundColor:v(e.percentage)})),c=S(()=>(e.strokeWidth/e.width*100).toFixed(1)),i=S(()=>e.type==="circle"||e.type==="dashboard"?Number.parseInt(`${50-Number.parseFloat(c.value)/2}`,10):0),u=S(()=>{const l=i.value,g=e.type==="dashboard";return`
          M 50 50
          m 0 ${g?"":"-"}${l}
          a ${l} ${l} 0 1 1 0 ${g?"-":""}${l*2}
          a ${l} ${l} 0 1 1 0 ${g?"":"-"}${l*2}
          `}),p=S(()=>2*Math.PI*i.value),$=S(()=>e.type==="dashboard"?.75:1),R=S(()=>`${-1*p.value*(1-$.value)/2}px`),C=S(()=>({strokeDasharray:`${p.value*$.value}px, ${p.value}px`,strokeDashoffset:R.value})),d=S(()=>({strokeDasharray:`${p.value*$.value*(e.percentage/100)}px, ${p.value}px`,strokeDashoffset:R.value,transition:"stroke-dasharray 0.6s ease 0s, stroke 0.6s ease, opacity ease 0.6s"})),w=S(()=>{let l;if(e.color)l=v(e.percentage);else switch(e.status){case"success":l="#13ce66";break;case"exception":l="#ff4949";break;case"warning":l="#e6a23c";break;default:l="#20a0ff"}return l}),n=S(()=>e.status==="warning"?fe:e.type==="line"?e.status==="success"?x:pe:e.status==="success"?te:ee),t=S(()=>e.type==="line"?12+e.strokeWidth*.4:e.width*.111111+2),r=S(()=>e.format(e.percentage)),v=l=>{var g;const{color:F}=e;if(typeof F=="function")return F(l);if(typeof F=="string")return F;{const q=100/F.length,W=F.map((_,N)=>typeof _=="string"?{color:_,percentage:(N+1)*q}:_).sort((_,N)=>_.percentage-N.percentage);for(const _ of W)if(_.percentage>l)return _.color;return(g=W[W.length-1])==null?void 0:g.color}},y=S(()=>({percentage:e.percentage}));return{ns:a,barStyle:s,relativeStrokeWidth:c,radius:i,trackPath:u,perimeter:p,rate:$,strokeDashoffset:R,trailPathStyle:C,circlePathStyle:d,stroke:w,statusIcon:n,progressTextSize:t,content:r,slotData:y}}}),ct=["aria-valuenow"],pt={viewBox:"0 0 100 100"},ft=["d","stroke","stroke-width"],vt=["d","stroke","opacity","stroke-linecap","stroke-width"],mt={key:0};function ht(e,a,s,c,i,u){const p=Te("el-icon");return f(),k("div",{class:m([e.ns.b(),e.ns.m(e.type),e.ns.is(e.status),{[e.ns.m("without-text")]:!e.showText,[e.ns.m("text-inside")]:e.textInside}]),role:"progressbar","aria-valuenow":e.percentage,"aria-valuemin":"0","aria-valuemax":"100"},[e.type==="line"?(f(),k("div",{key:0,class:m(e.ns.b("bar"))},[L("div",{class:m(e.ns.be("bar","outer")),style:H({height:`${e.strokeWidth}px`})},[L("div",{class:m([e.ns.be("bar","inner"),{[e.ns.bem("bar","inner","indeterminate")]:e.indeterminate}]),style:H(e.barStyle)},[(e.showText||e.$slots.default)&&e.textInside?(f(),k("div",{key:0,class:m(e.ns.be("bar","innerText"))},[P(e.$slots,"default",le(ie(e.slotData)),()=>[L("span",null,G(e.content),1)])],2)):b("v-if",!0)],6)],6)],2)):(f(),k("div",{key:1,class:m(e.ns.b("circle")),style:H({height:`${e.width}px`,width:`${e.width}px`})},[(f(),k("svg",pt,[L("path",{class:m(e.ns.be("circle","track")),d:e.trackPath,stroke:`var(${e.ns.cssVarName("fill-color-light")}, #e5e9f2)`,"stroke-width":e.relativeStrokeWidth,fill:"none",style:H(e.trailPathStyle)},null,14,ft),L("path",{class:m(e.ns.be("circle","path")),d:e.trackPath,stroke:e.stroke,fill:"none",opacity:e.percentage?1:0,"stroke-linecap":e.strokeLinecap,"stroke-width":e.relativeStrokeWidth,style:H(e.circlePathStyle)},null,14,vt)]))],6)),(e.showText||e.$slots.default)&&!e.textInside?(f(),k("div",{key:2,class:m(e.ns.e("text")),style:H({fontSize:`${e.progressTextSize}px`})},[P(e.$slots,"default",le(ie(e.slotData)),()=>[e.status?(f(),U(p,{key:1},{default:T(()=>[(f(),U(Le(e.statusIcon)))]),_:1})):(f(),k("span",mt,G(e.content),1))])],6)):b("v-if",!0)],10,ct)}var yt=X(dt,[["render",ht],["__file","/home/runner/work/element-plus/element-plus/packages/components/progress/src/progress.vue"]]);const gt=be(yt),bt="ElUpload";class kt extends Error{constructor(a,s,c,i){super(a),this.name="UploadAjaxError",this.status=s,this.method=c,this.url=i}}function ve(e,a,s){let c;return s.response?c=`${s.response.error||s.response}`:s.responseText?c=`${s.responseText}`:c=`fail to ${a.method} ${e} ${s.status}`,new kt(c,s.status,a.method,e)}function $t(e){const a=e.responseText||e.response;if(!a)return a;try{return JSON.parse(a)}catch{return a}}const wt=e=>{typeof XMLHttpRequest=="undefined"&&ne(bt,"XMLHttpRequest is undefined");const a=new XMLHttpRequest,s=e.action;a.upload&&a.upload.addEventListener("progress",u=>{const p=u;p.percent=u.total>0?u.loaded/u.total*100:0,e.onProgress(p)});const c=new FormData;if(e.data)for(const[u,p]of Object.entries(e.data))Array.isArray(p)?c.append(u,...p):c.append(u,p);c.append(e.filename,e.file,e.file.name),a.addEventListener("error",()=>{e.onError(ve(s,e,a))}),a.addEventListener("load",()=>{if(a.status<200||a.status>=300)return e.onError(ve(s,e,a));e.onSuccess($t(a))}),a.open(e.method,s,!0),e.withCredentials&&"withCredentials"in a&&(a.withCredentials=!0);const i=e.headers||{};if(i instanceof Headers)i.forEach((u,p)=>a.setRequestHeader(p,u));else for(const[u,p]of Object.entries(i))Ae(p)||a.setRequestHeader(u,String(p));return a.send(c),a},$e=["text","picture","picture-card"];let Ct=1;const we=()=>Date.now()+Ct++,Ce=A({action:{type:String,default:"#"},headers:{type:h(Object)},method:{type:String,default:"post"},data:{type:Object,default:()=>J({})},multiple:{type:Boolean,default:!1},name:{type:String,default:"file"},drag:{type:Boolean,default:!1},withCredentials:Boolean,showFileList:{type:Boolean,default:!0},accept:{type:String,default:""},type:{type:String,default:"select"},fileList:{type:h(Array),default:()=>J([])},autoUpload:{type:Boolean,default:!0},listType:{type:String,values:$e,default:"text"},httpRequest:{type:h(Function),default:wt},disabled:Boolean,limit:Number}),St=A(z(B({},Ce),{beforeUpload:{type:h(Function),default:E},beforeRemove:{type:h(Function)},onRemove:{type:h(Function),default:E},onChange:{type:h(Function),default:E},onPreview:{type:h(Function),default:E},onSuccess:{type:h(Function),default:E},onProgress:{type:h(Function),default:E},onError:{type:h(Function),default:E},onExceed:{type:h(Function),default:E}})),Et=A({files:{type:h(Array),default:()=>J([])},disabled:{type:Boolean,default:!1},handlePreview:{type:h(Function),default:E},listType:{type:String,values:$e,default:"text"}}),Pt={remove:e=>!!e},Rt=["onKeydown"],Ft=["src"],Tt=["onClick"],Lt=["onClick"],Ut=["onClick"],_t={name:"ElUploadList"},Dt=O(z(B({},_t),{props:Et,emits:Pt,setup(e,{emit:a}){const s=e,{t:c}=qe(),i=V("upload"),u=V("icon"),p=V("list"),$=se(!1),R=d=>{s.handlePreview(d)},C=d=>{a("remove",d)};return(d,w)=>(f(),U(De,{tag:"ul",class:m([o(i).b("list"),o(i).bm("list",d.listType),o(i).is("disabled",d.disabled)]),name:o(p).b()},{default:T(()=>[(f(!0),k(_e,null,Ue(d.files,n=>(f(),k("li",{key:n.uid||n.name,class:m([o(i).be("list","item"),o(i).is(n.status),{focusing:$.value}]),tabindex:"0",onKeydown:ge(t=>!d.disabled&&C(n),["delete"]),onFocus:w[0]||(w[0]=t=>$.value=!0),onBlur:w[1]||(w[1]=t=>$.value=!1),onClick:w[2]||(w[2]=t=>$.value=!1)},[P(d.$slots,"default",{file:n},()=>[d.listType==="picture"||n.status!=="uploading"&&d.listType==="picture-card"?(f(),k("img",{key:0,class:m(o(i).be("list","item-thumbnail")),src:n.url,alt:""},null,10,Ft)):b("v-if",!0),d.listType!=="picture"&&(n.status==="uploading"||d.listType!=="picture-card")?(f(),k("div",{key:1,class:m(o(i).be("list","item-info"))},[L("a",{class:m(o(i).be("list","item-name")),onClick:M(t=>R(n),["prevent"])},[D(o(j),{class:m(o(u).m("document"))},{default:T(()=>[D(o(it))]),_:1},8,["class"]),L("span",{class:m(o(i).be("list","item-file-name"))},G(n.name),3)],10,Tt),n.status==="uploading"?(f(),U(o(gt),{key:0,type:d.listType==="picture-card"?"circle":"line","stroke-width":d.listType==="picture-card"?6:2,percentage:Number(n.percentage),style:H(d.listType==="picture-card"?"":"margin-top: 0.5rem")},null,8,["type","stroke-width","percentage","style"])):b("v-if",!0)],2)):b("v-if",!0),L("label",{class:m(o(i).be("list","item-status-label"))},[d.listType==="text"?(f(),U(o(j),{key:0,class:m([o(u).m("upload-success"),o(u).m("circle-check")])},{default:T(()=>[D(o(x))]),_:1},8,["class"])):["picture-card","picture"].includes(d.listType)?(f(),U(o(j),{key:1,class:m([o(u).m("upload-success"),o(u).m("check")])},{default:T(()=>[D(o(te))]),_:1},8,["class"])):b("v-if",!0)],2),d.disabled?b("v-if",!0):(f(),U(o(j),{key:2,class:m(o(u).m("close")),onClick:t=>C(n)},{default:T(()=>[D(o(ee))]),_:2},1032,["class","onClick"])),b(" Due to close btn only appears when li gets focused disappears after li gets blurred, thus keyboard navigation can never reach close btn"),b(" This is a bug which needs to be fixed "),b(" TODO: Fix the incorrect navigation interaction "),d.disabled?b("v-if",!0):(f(),k("i",{key:3,class:m(o(u).m("close-tip"))},G(o(c)("el.upload.deleteTip")),3)),d.listType==="picture-card"?(f(),k("span",{key:4,class:m(o(i).be("list","item-actions"))},[L("span",{class:m(o(i).be("list","item-preview")),onClick:t=>d.handlePreview(n)},[D(o(j),{class:m(o(u).m("zoom-in"))},{default:T(()=>[D(o(We))]),_:1},8,["class"])],10,Lt),d.disabled?b("v-if",!0):(f(),k("span",{key:0,class:m(o(i).be("list","item-delete")),onClick:t=>C(n)},[D(o(j),{class:m(o(u).m("delete"))},{default:T(()=>[D(o(st))]),_:1},8,["class"])],10,Ut))],2)):b("v-if",!0)])],42,Rt))),128)),P(d.$slots,"append")]),_:3},8,["class","name"]))}}));var me=X(Dt,[["__file","/home/runner/work/element-plus/element-plus/packages/components/upload/src/upload-list.vue"]]);const Bt=A({disabled:{type:Boolean,default:!1}}),zt={file:e=>Be(e)},Ot=["onDrop","onDragover"],Nt={name:"ElUploadDrag"},Ht=O(z(B({},Nt),{props:Bt,emits:zt,setup(e,{emit:a}){const s=e,c="ElUploadDrag",i=ze(ke);i||ne(c,"usage: <el-upload><el-upload-dragger /></el-upload>");const u=V("upload"),p=se(!1),$=C=>{if(s.disabled)return;p.value=!1;const d=Array.from(C.dataTransfer.files),w=i.accept.value;if(!w){a("file",d);return}const n=d.filter(t=>{const{type:r,name:v}=t,y=v.includes(".")?`.${v.split(".").pop()}`:"",l=r.replace(/\/.*$/,"");return w.split(",").map(g=>g.trim()).filter(g=>g).some(g=>g.startsWith(".")?y===g:/\/\*$/.test(g)?l===g.replace(/\/\*$/,""):/^[^/]+\/[^/]+$/.test(g)?r===g:!1)});a("file",n)},R=()=>{s.disabled||(p.value=!0)};return(C,d)=>(f(),k("div",{class:m([o(u).b("dragger"),o(u).is("dragover",p.value)]),onDrop:M($,["prevent"]),onDragover:M(R,["prevent"]),onDragleave:d[0]||(d[0]=M(w=>p.value=!1,["prevent"]))},[P(C.$slots,"default")],42,Ot))}}));var jt=X(Ht,[["__file","/home/runner/work/element-plus/element-plus/packages/components/upload/src/upload-dragger.vue"]]);const It=A(z(B({},Ce),{fileList:{type:h(Array),default:()=>J([])},beforeUpload:{type:h(Function),default:E},onRemove:{type:h(Function),default:E},onStart:{type:h(Function),default:E},onSuccess:{type:h(Function),default:E},onProgress:{type:h(Function),default:E},onError:{type:h(Function),default:E},onExceed:{type:h(Function),default:E}})),Mt=["onKeydown"],Vt=["name","multiple","accept"],At={name:"ElUploadContent",inheritAttrs:!1},qt=O(z(B({},At),{props:It,setup(e,{expose:a}){const s=e,c=V("upload"),i=Z({}),u=Z(),p=t=>{if(t.length===0)return;const{autoUpload:r,limit:v,fileList:y,multiple:l,onStart:g,onExceed:F}=s;if(v&&y.length+t.length>v){F(t,y);return}l||(t=t.slice(0,1));for(const q of t){const I=q;I.uid=we(),g(I),r&&$(I)}},$=async t=>{if(u.value.value="",!s.beforeUpload)return R(t);let r;try{r=await s.beforeUpload(t)}catch{r=!1}if(r===!1){s.onRemove(t);return}let v=t;if(r instanceof Blob){r instanceof File?v=r:v=new File([r],t.name,{type:t.type});for(const y of Object.keys(t))v[y]=t[y]}R(t)},R=t=>{const{headers:r,data:v,method:y,withCredentials:l,name:g,action:F,onProgress:q,onSuccess:I,onError:W,httpRequest:_}=s,{uid:N}=t,Q={headers:r||{},withCredentials:l,file:t,data:v,method:y,filename:g,action:F,onProgress:K=>{q(K,t)},onSuccess:K=>{I(K,t),delete i.value[N]},onError:K=>{W(K,t),delete i.value[N]}},Y=_(Q);i.value[N]=Y,Y instanceof Promise&&Y.then(Q.onSuccess,Q.onError)},C=t=>{const r=t.target.files;!r||p(Array.from(r))},d=()=>{s.disabled||(u.value.value="",u.value.click())},w=()=>{d()};return a({abort:t=>{Ie(i.value).filter(t?([v])=>String(t.uid)===v:()=>!0).forEach(([v,y])=>{y instanceof XMLHttpRequest&&y.abort(),delete i.value[v]})},upload:$}),(t,r)=>(f(),k("div",{class:m([o(c).b(),o(c).m(t.listType),o(c).is("drag",t.drag)]),tabindex:"0",onClick:d,onKeydown:ge(M(w,["self"]),["enter","space"])},[t.drag?(f(),U(jt,{key:0,disabled:t.disabled,onFile:p},{default:T(()=>[P(t.$slots,"default")]),_:3},8,["disabled"])):P(t.$slots,"default",{key:1}),L("input",{ref_key:"inputRef",ref:u,class:m(o(c).e("input")),name:t.name,multiple:t.multiple,accept:t.accept,type:"file",onChange:C,onClick:r[0]||(r[0]=M(()=>{},["stop"]))},null,42,Vt)],42,Mt))}}));var he=X(qt,[["__file","/home/runner/work/element-plus/element-plus/packages/components/upload/src/upload-content.vue"]]);const ye="ElUpload",Wt=e=>{var a;(a=e.url)!=null&&a.startsWith("blob:")&&URL.revokeObjectURL(e.url)},Kt=(e,a)=>{const s=se([]),c=n=>s.value.find(t=>t.uid===n.uid);function i(n){var t;(t=a.value)==null||t.abort(n)}function u(n=["ready","uploading","success","fail"]){s.value=s.value.filter(t=>!n.includes(t.status))}const p=(n,t)=>{const r=c(t);!r||(r.status="fail",s.value.splice(s.value.indexOf(r),1),e.onError(n,r,s.value),e.onChange(r,s.value))},$=(n,t)=>{const r=c(t);!r||(e.onProgress(n,r,s.value),r.status="uploading",r.percentage=Math.round(n.percent))},R=(n,t)=>{const r=c(t);!r||(r.status="success",r.response=n,e.onSuccess(n,r,s.value),e.onChange(r,s.value))},C=n=>{const t={name:n.name,percentage:0,status:"ready",size:n.size,raw:n,uid:n.uid};if(e.listType==="picture-card"||e.listType==="picture")try{t.url=URL.createObjectURL(n)}catch(r){Me(ye,r.message),e.onError(r,t,s.value)}s.value.push(t),e.onChange(t,s.value)},d=async n=>{const t=n instanceof File?c(n):n;t||ne(ye,"file to be removed not found");const r=v=>{i(v);const y=s.value;y.splice(y.indexOf(v),1),e.onRemove(v,y),Wt(v)};e.beforeRemove?await e.beforeRemove(t,s.value)!==!1&&r(t):r(t)};function w(){s.value.filter(({status:n})=>n==="ready").forEach(({raw:n})=>{var t;return n&&((t=a.value)==null?void 0:t.upload(n))})}return ue(()=>e.listType,n=>{n!=="picture-card"&&n!=="picture"||(s.value=s.value.map(t=>{const{raw:r,url:v}=t;if(!v&&r)try{t.url=URL.createObjectURL(r)}catch(y){e.onError(y,t,s.value)}return t}))}),ue(()=>e.fileList,n=>{for(const t of n)t.uid||(t.uid=we()),t.status||(t.status="success");s.value=n},{immediate:!0,deep:!0}),{abort:i,clearFiles:u,handleError:p,handleProgress:$,handleStart:C,handleSuccess:R,handleRemove:d,submit:w,uploadFiles:s}},Xt={name:"ElUpload"},Gt=O(z(B({},Xt),{props:St,setup(e,{expose:a}){const s=e,c=Oe(),i=Ve(),u=Z(),{abort:p,submit:$,clearFiles:R,uploadFiles:C,handleStart:d,handleError:w,handleRemove:n,handleSuccess:t,handleProgress:r}=Kt(s,u),v=S(()=>s.listType==="picture-card"),y=S(()=>z(B({},s),{onStart:d,onProgress:r,onSuccess:t,onError:w,onRemove:n}));return Ne(()=>{C.value.forEach(({url:l})=>{l!=null&&l.startsWith("blob:")&&URL.revokeObjectURL(l)})}),He(ke,{accept:je(s,"accept")}),a({abort:p,submit:$,clearFiles:R,handleStart:d,handleRemove:n}),(l,g)=>(f(),k("div",null,[o(v)&&l.showFileList?(f(),U(me,{key:0,disabled:o(i),"list-type":l.listType,files:o(C),"handle-preview":l.onPreview,onRemove:o(n)},de({append:T(()=>[l.listType==="picture-card"?(f(),U(he,ce({key:0,ref_key:"uploadRef",ref:u},o(y)),{default:T(()=>[o(c).trigger?P(l.$slots,"trigger",{key:0}):b("v-if",!0),!o(c).trigger&&o(c).default?P(l.$slots,"default",{key:1}):b("v-if",!0)]),_:3},16)):b("v-if",!0)]),_:2},[l.$slots.file?{name:"default",fn:T(({file:F})=>[P(l.$slots,"file",{file:F})])}:void 0]),1032,["disabled","list-type","files","handle-preview","onRemove"])):b("v-if",!0),l.listType!=="picture-card"?(f(),U(he,ce({key:1,ref_key:"uploadRef",ref:u},o(y)),{default:T(()=>[o(c).trigger?P(l.$slots,"trigger",{key:0}):b("v-if",!0),!o(c).trigger&&o(c).default?P(l.$slots,"default",{key:1}):b("v-if",!0)]),_:3},16)):b("v-if",!0),l.$slots.trigger?P(l.$slots,"default",{key:2}):b("v-if",!0),P(l.$slots,"tip"),!o(v)&&l.showFileList?(f(),U(me,{key:3,disabled:o(i),"list-type":l.listType,files:o(C),"handle-preview":l.onPreview,onRemove:o(n)},de({_:2},[l.$slots.file?{name:"default",fn:T(({file:F})=>[P(l.$slots,"file",{file:F})])}:void 0]),1032,["disabled","list-type","files","handle-preview","onRemove"])):b("v-if",!0)]))}}));var Jt=X(Gt,[["__file","/home/runner/work/element-plus/element-plus/packages/components/upload/src/upload.vue"]]);const ns=be(Jt);export{ns as E,te as c};