"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[846],{3905:(e,t,r)=>{r.d(t,{Zo:()=>p,kt:()=>s});var n=r(7294);function a(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function o(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function l(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?o(Object(r),!0).forEach((function(t){a(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):o(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function i(e,t){if(null==e)return{};var r,n,a=function(e,t){if(null==e)return{};var r,n,a={},o=Object.keys(e);for(n=0;n<o.length;n++)r=o[n],t.indexOf(r)>=0||(a[r]=e[r]);return a}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(n=0;n<o.length;n++)r=o[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(a[r]=e[r])}return a}var c=n.createContext({}),m=function(e){var t=n.useContext(c),r=t;return e&&(r="function"==typeof e?e(t):l(l({},t),e)),r},p=function(e){var t=m(e.components);return n.createElement(c.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},d=n.forwardRef((function(e,t){var r=e.components,a=e.mdxType,o=e.originalType,c=e.parentName,p=i(e,["components","mdxType","originalType","parentName"]),d=m(r),s=a,f=d["".concat(c,".").concat(s)]||d[s]||u[s]||o;return r?n.createElement(f,l(l({ref:t},p),{},{components:r})):n.createElement(f,l({ref:t},p))}));function s(e,t){var r=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var o=r.length,l=new Array(o);l[0]=d;var i={};for(var c in t)hasOwnProperty.call(t,c)&&(i[c]=t[c]);i.originalType=e,i.mdxType="string"==typeof e?e:a,l[1]=i;for(var m=2;m<o;m++)l[m]=r[m];return n.createElement.apply(null,l)}return n.createElement.apply(null,r)}d.displayName="MDXCreateElement"},3356:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>c,contentTitle:()=>l,default:()=>u,frontMatter:()=>o,metadata:()=>i,toc:()=>m});var n=r(7462),a=(r(7294),r(3905));const o={sidebar_position:1},l="tail",i={unversionedId:"commands/tail",id:"commands/tail",title:"tail",description:"To stream block data from a local/remote Cardano node in real-time, you can use `tail` command.",source:"@site/docs/commands/tail.md",sourceDirName:"commands",slug:"/commands/tail",permalink:"/docs/commands/tail",draft:!1,editUrl:"https://github.com/bloxbean/yaci-cli/tree/main/docs/docs/commands/tail.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"tutorialSidebar",previous:{title:"Commands",permalink:"/docs/category/commands"},next:{title:"Local Cluster",permalink:"/docs/commands/local-cluster"}},c={},m=[{value:"1. Change background",id:"1-change-background",level:2},{value:"2. Stream from a Cardano node",id:"2-stream-from-a-cardano-node",level:2},{value:"2.1 Stream from a public network using public relay",id:"21-stream-from-a-public-network-using-public-relay",level:3},{value:"2.2 Stream from a public network using your own Cardano node",id:"22-stream-from-a-public-network-using-your-own-cardano-node",level:3},{value:"2.3 Stream from a private network",id:"23-stream-from-a-private-network",level:3}],p={toc:m};function u(e){let{components:t,...r}=e;return(0,a.kt)("wrapper",(0,n.Z)({},p,r,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"tail"},"tail"),(0,a.kt)("p",null,"To stream block data from a local/remote Cardano node in real-time, you can use ",(0,a.kt)("inlineCode",{parentName:"p"},"tail")," command."),(0,a.kt)("p",null,'Once you start yaci-cli, you should see a prompt "yaci-cli:>"'),(0,a.kt)("h2",{id:"1-change-background"},"1. Change background"),(0,a.kt)("p",null,"For terminal with light background, use --color-mode option."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre"},"yaci-cli:> tail --color-mode light\n")),(0,a.kt)("h2",{id:"2-stream-from-a-cardano-node"},"2. Stream from a Cardano node"),(0,a.kt)("p",null,'Use "tail" command to stream from a Cardano node. The tail command supports :-'),(0,a.kt)("p",null,"a> Stream from a public network (mainnet, legacy_testnet, prepod, preview) using a known public relay"),(0,a.kt)("p",null,"b> Stream from a public network using your own Cardano node"),(0,a.kt)("p",null,"c> Stream from a private network"),(0,a.kt)("h3",{id:"21-stream-from-a-public-network-using-public-relay"},"2.1 Stream from a public network using public relay"),(0,a.kt)("p",null,"Specify the network name to stream using a public relay. The supported networks are  mainnet / legacy_testnet / prepod/ preview"),(0,a.kt)("p",null,"Default network: mainnet"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre"},"yaci-cli> tail    \n\nyaci-cli> tail --network legacy_testnet\n\nyaci-cli> tail --network prepod\n\nyaci-cli> tail --network preview\n")),(0,a.kt)("h3",{id:"22-stream-from-a-public-network-using-your-own-cardano-node"},"2.2 Stream from a public network using your own Cardano node"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre"},"yaci-cli> tail --network mainnet --host <Cardano Node Host> --port <Cardano Node Port)\n\n")),(0,a.kt)("h3",{id:"23-stream-from-a-private-network"},"2.3 Stream from a private network"),(0,a.kt)("p",null,"To stream data from a private network, provide host, port, protocol magic, known host, known port"),(0,a.kt)("p",null,"Example:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre"},"yaci-cli:>tail --host localhost --port 30000 --protocol-magic 1 --known-slot 7055961 --known-blockhash f5753d8e7df48ed77eb1bc886e9b42c629e8a885ee88cfc994c127d2dff19641\n")))}u.isMDXComponent=!0}}]);