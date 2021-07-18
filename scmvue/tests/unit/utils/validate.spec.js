import { validUserCode, isExternal } from '@/utils/validate.js'

describe('Utils:validate', () => {
  it('validUserCode', () => {
    expect(validUserCode('admin')).toBe(true)
    expect(validUserCode('editor')).toBe(true)
    expect(validUserCode('xxxx')).toBe(false)
  })
  it('isExternal', () => {
    expect(isExternal('https://github.com/PanJiaChen/vue-element-admin')).toBe(true)
    expect(isExternal('http://github.com/PanJiaChen/vue-element-admin')).toBe(true)
    expect(isExternal('github.com/PanJiaChen/vue-element-admin')).toBe(false)
    expect(isExternal('/dashboard')).toBe(false)
    expect(isExternal('./dashboard')).toBe(false)
    expect(isExternal('dashboard')).toBe(false)
  })
})
