import React from 'react';
import IconFont from '@/components/IconFont';

export const listConvertOptions = (list) => {
  return list.map((item) => {
    const temp = {};
    temp['label'] = item.name;
    temp['value'] = item.id;
    return temp;
  });
};

export function listConvertTree(list, disabledFun) {
  const root = [];
  if (list && list.length) {
    const group = {};
    const nList = list.map((item) => {
      const { id, parentId, name, icon, key } = item;
      const nItem = {
        key: key || `${id}`,
        value: id,
        title: name,
        icon: () => {
          return icon ? <IconFont type={icon} /> : null;
        },
        parentId,
        origin: item,
        disabled: disabledFun ? disabledFun(item) : false,
      };
      group[item.id] = nItem;
      return nItem;
    });

    nList.forEach((item) => {
      if (item.parentId !== null && item.parentId !== undefined && group[item.parentId]) {
        const parent = group[item.parentId];
        if (!parent.children) {
          parent.children = [];
        }
        parent.children.push(item);
      } else {
        root.push(item);
      }
    });
  }
  return setLevel(root);
}

export function treeConver(list,level){
  list.sort(function (a,b){
    return a.typeId-b.typeId;
  })
  const root = [];
  if (list && list.length) {
    list.map((item)=>{
      const{typeId,name,id}=item
      switch (typeId) {
        case level:
          const nItem = {
            key: name,
            value: name+'-'+id.split('-')[1]
          }
          root.push(nItem)
          break;
        case level:
          const nItem1 = {
            key: name,
            value: name  +'-'+id.split('-')[1]        }
          root.push(nItem1)
          break;
        case level:
          const nItem2 = {
            key: name,
            value: name +'-'+id.split('-')[1]        }
          root.push(nItem2)
          break;
      }
    })
    return root;
  }
}

function setLevel(list, level = 1) {
  if (list && list.length) {
    const nList = list.map((item) => {
      const { children, origin } = item;
      const nItem = {
        ...item,
        level,
        origin: { ...origin, level },
      };
      if (item.children) {
        nItem.children = setLevel(children, level + 1);
      }
      return nItem;
    });
    return nList;
  }
  return list;
}

// Tree转换
export function convertToTree(data, disabledFun, level = 1) {
  if (!data) {
    return [];
  }
  return data.map((item) => {
    const { id, name, icon, children, key } = item;
    const nItem = {
      key: key || `${id}`,
      value: id,
      title: name,
      icon: () => {
        return icon ? <IconFont type={icon} /> : null;
      },
      origin: { ...item, level },
      disabled: disabledFun ? disabledFun(item) : false,
      level,
    };
    if (item.children) {
      nItem.children = convertToTree(children, disabledFun, level + 1);
    }
    return nItem;
  });
}

function defaultFilterFunc(searchValue, title) {
  return title.includes(searchValue);
}

function defaultMatchTitleFunc(searchValue, title) {
  const index = title.indexOf(searchValue);
  const beforeStr = title.substr(0, index);
  const afterStr = title.substr(index + searchValue.length);
  return index > -1 ? (
    <span>
      {beforeStr}
      <span style={{ color: '#f50' }}>{searchValue}</span>
      {afterStr}
    </span>
  ) : (
    <span>{title}</span>
  );
}

// Tree搜索
export function getFilterTree(
  data,
  searchValue,
  filterFunc = defaultFilterFunc,
  matchTitleFunc = defaultMatchTitleFunc,
) {
  console.log('data', data);
  if (!data) {
    return [];
  }
  if (!searchValue) {
    return data;
  }

  function mapFiltered(node) {
    if (!node) return null;
    let match = false;
    if (filterFunc(searchValue, node.title)) {
      match = true;
    }
    const children = node.children ? node.children.map(mapFiltered).filter((n) => n) : null;
    if (match || (children && children.length)) {
      return {
        ...node,
        title: match ? matchTitleFunc(searchValue, node.title) : node.title,
        children,
      };
    }
    return null;
  }

  return data.map(mapFiltered).filter((node) => node);
}

// Cascader转换
export function convertToCascader(data) {
  if (!data) {
    return [];
  }
  return data.map((item) => {
    const nItem = {
      value: String(item.id),
      label: item.name,
    };
    if (item.children) {
      nItem.children = convertToCascader(item.children);
    }
    return nItem;
  });
}

// Cascader转换
export function convertListToCascader(list, disabledFun) {
  const root = [];
  if (list && list.length > 0) {
    const group = {};
    const nList = list.map((item) => {
      const { id, parentId, name, key } = item;
      const nItem = {
        key: key || `${id}`,
        value: String(id),
        label: name,
        parentId,
        origin: item,
        disabled: disabledFun ? disabledFun(item) : false,
      };
      group[item.id] = nItem;
      return nItem;
    });

    nList.forEach((item) => {
      if (item.parentId !== null && item.parentId !== undefined && group[item.parentId]) {
        const parent = group[item.parentId];
        if (!parent.children) {
          parent.children = [];
        }
        parent.children.push(item);
      } else {
        root.push(item);
      }
    });
  }
  return root;
}

// 状态检测Cascader转换
export function StatusDetectionListToCascader2(data) {
  if (!data) {
    return [];
  }
  return data.map((item) => {
    const nItem = {
      value: item.name,
      label: item.name,
    };
    return nItem;
  });
}

// 状态检测Cascader转换
export function StatusDetectionListToCascader(data) {
  if (!data) {
    return [];
  }
  return data.map((item) => {
    const nItem = {
      value: String(item.deviceId),
      label: item.deviceName,
    };
    if (item.list) {
      nItem.children = StatusDetectionListToCascader2(item.list);
    }
    return nItem;
  });
}
